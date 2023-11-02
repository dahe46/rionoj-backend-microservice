package com.rion.rionojbackenduserservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rion.rionojbackendcommon.common.ErrorCode;
import com.rion.rionojbackendcommon.constant.CommonConstant;
import com.rion.rionojbackendcommon.constant.HttpConstant;
import com.rion.rionojbackendcommon.constant.RedisConstant;
import com.rion.rionojbackendcommon.constant.RedisKey;
import com.rion.rionojbackendcommon.exception.BusinessException;
import com.rion.rionojbackendcommon.utils.JwtUtils;
import com.rion.rionojbackendcommon.utils.RedisUtils;
import com.rion.rionojbackendcommon.utils.SqlUtils;
import com.rion.rionojbackendmodel.model.dto.user.UserQueryRequest;
import com.rion.rionojbackendmodel.model.entity.User;
import com.rion.rionojbackendmodel.model.enums.UserRoleEnum;
import com.rion.rionojbackendmodel.model.vo.LoginUserVO;
import com.rion.rionojbackendmodel.model.vo.UserVO;
import com.rion.rionojbackenduserservice.mapper.UserMapper;
import com.rion.rionojbackenduserservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * 用户服务实现
 *
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 盐值，密码加盐
     */
    private static final String SALT = "rion";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号过短");
        }
        if (userPassword.length() < 4 || checkPassword.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码过短");
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        }
        synchronized (userAccount.intern()) {
            // 账户不能重复
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", userAccount);
            long count = this.baseMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
            }
            // 2. 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
            // 3. 插入数据
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserPassword(encryptPassword);
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return user.getId();
        }
    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号过短");
        }
        if (userPassword.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码过短");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        Long id = user.getId();
        String key = RedisKey.getKey(RedisConstant.USER_TOKEN_KEY, id.toString());
        // 生成Token
        String token = JwtUtils.createToken(id);
        // 3. 将Token存入redis(string 类型)
        RedisUtils.set(key, token, RedisConstant.TOKEN_EXPIRED, TimeUnit.MINUTES);

        LoginUserVO loginUserVO = this.getLoginUserVO(user);
        loginUserVO.setToken(token);
        return loginUserVO;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 从数据库查询
        String token = request.getHeader(HttpConstant.TOKEN_HEADER_NAME);
        Long userId = JwtUtils.getId(token);
        String key = RedisKey.getKey(RedisConstant.USER_TOKEN_KEY, userId.toString());
        String redisToken = RedisUtils.get(key);
        if (!StringUtils.isNotEmpty(redisToken)) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        User currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public User getLoginUserPermitNull(HttpServletRequest request) {
        String token = request.getHeader(HttpConstant.TOKEN_HEADER_NAME);
        Long userId = JwtUtils.getId(token);
        return this.getById(userId);
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        String token = request.getHeader(HttpConstant.TOKEN_HEADER_NAME);
        Long userId = JwtUtils.getId(token);
        User currentUser = this.getById(userId);
        return isAdmin(currentUser);
    }

    @Override
    public boolean isAdmin(User user) {
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        String token = request.getHeader(HttpConstant.TOKEN_HEADER_NAME);
        // 移除登录态
        Long id = JwtUtils.getId(token);
        String key = RedisKey.getKey(RedisConstant.USER_TOKEN_KEY, id.toString());
        RedisUtils.deleteKey(key);
        return true;
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVO(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String mpOpenId = userQueryRequest.getMpOpenId();
        String userName = userQueryRequest.getUserName();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(mpOpenId), "mpOpenId", mpOpenId);
        queryWrapper.eq(StringUtils.isNotBlank(userRole), "userRole", userRole);
        queryWrapper.like(StringUtils.isNotBlank(userProfile), "userProfile", userProfile);
        queryWrapper.like(StringUtils.isNotBlank(userName), "userName", userName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

}
