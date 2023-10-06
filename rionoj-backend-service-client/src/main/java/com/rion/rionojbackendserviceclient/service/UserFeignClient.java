package com.rion.rionojbackendserviceclient.service;

import com.rion.rionojbackendcommon.common.ErrorCode;
import com.rion.rionojbackendcommon.constant.HttpConstant;
import com.rion.rionojbackendcommon.constant.RedisConstant;
import com.rion.rionojbackendcommon.constant.RedisKey;
import com.rion.rionojbackendcommon.exception.BusinessException;
import com.rion.rionojbackendcommon.utils.JwtUtils;
import com.rion.rionojbackendcommon.utils.RedisUtils;
import com.rion.rionojbackendmodel.model.entity.User;
import com.rion.rionojbackendmodel.model.enums.UserRoleEnum;
import com.rion.rionojbackendmodel.model.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 用户服务
 *
 */
@FeignClient(name = "rionoj-backend-user-service", path = "/api/user/inner")
public interface UserFeignClient {

    /**
     * 根据 id 获取用户
     *
     * @param userId 用户 id
     * @return {@code User}
     */
    @GetMapping("/get/id")
    User getById(@RequestParam("userId") long userId);

    /**
     * 根据 id 获取用户列表
     *
     * @param idList  id列表
     * @return {@code List<User>}
     */
    @GetMapping("/get/ids")
    List<User> listByIds(@RequestParam("idList") Collection<Long> idList);

    /**
     * 获取当前登录用户
     *
     * @param request 请求
     * @return {@code User}
     */
    default User getLoginUser(HttpServletRequest request) {
        String token = request.getHeader(HttpConstant.TOKEN_HEADER_NAME);
        if (!StringUtils.isNotEmpty(token)) {
            return null;
        }
        Long userId = JwtUtils.getId(token);
        String key = RedisKey.getKey(RedisConstant.USER_TOKEN_KEY, userId.toString());
        String redisToken = RedisUtils.get(key);
        if (!StringUtils.isNotEmpty(redisToken)) {
            return null;
        }
        User currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    /**
     * 是否为管理员
     *
     * @param user 用户
     * @return boolean
     */
    default boolean isAdmin(User user) {
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }

    /**
     * 获取脱敏的用户信息
     *
     * @param user 用户
     * @return {@code UserVO}
     */
    default UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

}
