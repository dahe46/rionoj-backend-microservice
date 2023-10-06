package com.rion.rionojbackendcommon.utils;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 *
 * @author Rion
 * @date 2023/6/06
 */
public class RedisUtils {
    private static StringRedisTemplate stringRedisTemplate;

    static {
        RedisUtils.stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
    }
    /**
     * 将值存入redis（string）并设置过期时间
     *
     * @param key  键
     * @param value   值
     * @param timeout 过期时间数
     * @param unit    单位
     */
    public static void set(String key, Object value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, objToStr(value), timeout, unit);
    }

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return {@code String}
     */
    public static String get(String key) {
        System.out.println(key);
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 指定key是否存在
     *
     * @param key 键
     * @return boolean
     */
    public static boolean hasKey(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
    }

    /**
     * 获取过期时间
     *
     * @param key  键
     * @param unit 时间单位
     * @return {@code Long}
     */
    public static Long getExpire(String key, TimeUnit unit) {
        return stringRedisTemplate.getExpire(key, unit);
    }

    /**
     * 设置过期时间
     *
     * @param key     钥匙
     * @param timeout 超时
     * @param unit    单位
     * @return {@code Boolean}
     */
    public static Boolean setExpire(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.expire(key, timeout, unit);
    }

    /**
     * 删除密钥
     *
     * @param key 钥匙
     * @return {@code Boolean}
     */
    public static Boolean deleteKey(String key) {
        return stringRedisTemplate.delete(key);
    }

    public static String objToStr(Object o) {
        return JSONUtil.toJsonStr(o);
    }
}
