package com.rion.rionojbackendcommon.constant;

public class RedisConstant {

    /**
     * 用户令牌键
     */
    public static final String USER_TOKEN_KEY = "userToken:uid:";

    /**
     * 令牌过期时间数
     */
    public static final long TOKEN_EXPIRED = 30L;

    /**
     * 令牌续期时间
     */
    public static final long TOKEN_RENEWAL_TIME  = 20L;

    /**
     * 令牌有效期剩余时间
     */
    public static final long TOKEN_REMAINDER = 10L;

}
