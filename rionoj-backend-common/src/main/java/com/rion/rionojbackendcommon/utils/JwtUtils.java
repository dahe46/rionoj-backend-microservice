package com.rion.rionojbackendcommon.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * jwt的token生成与解析工具类
 *
 * @author Rion
 * @date 2023/06/12
 */

public class JwtUtils {

    private static final String secret = "Rion";
    private static final String UID_CLAIM = "uid";
    private static final String CREATE_TIME = "createTime";
    private final static Integer TOKEN_EXPIRED = 30;

    /**
     * JWT生成Token
     * <p>
     * JWT构成: header, payload, signature
     *
     * @param uid UID
     * @return {@code String}
     */
    public static String createToken(Long uid) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, TOKEN_EXPIRED);
        return JWT.create()
                .withClaim(UID_CLAIM, uid)
                .withClaim(CREATE_TIME, new Date())
//                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(secret));
    }

    public static void main(String[] args) {
        String token = createToken(1702217428685066241L);
        System.out.println(token);
    }

    /**
     * 验证令牌
     *
     * @param token 令 牌
     * @return {@code Map<String, Claim>}
     * @throws JWTVerificationException JWT验证异常
     */
    public static Map<String, Claim> verifyToken(String token) throws JWTVerificationException {
        if (!StringUtils.isNotEmpty(token)) {
            return null;
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT jwt = verifier.verify(token);
//        Date expiresAt = jwt.getExpiresAt();
//        System.out.println("token过期时间：" + expiresAt);
        return jwt.getClaims();
    }

    /**
     * 根据Token获取uid
     *
     * @param token 令 牌
     * @return uid
     */
    public static Long getId(String token) {
        return Optional.ofNullable(verifyToken(token))
                .map(map -> map.get(UID_CLAIM))
                .map(Claim::asLong)
                .orElse(null);
    }

}
