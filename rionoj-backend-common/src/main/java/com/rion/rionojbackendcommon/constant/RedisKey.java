package com.rion.rionojbackendcommon.constant;

/**
 * redis key 构建
 *
 * @author Rion
 * @date 2023/06/06
 */
public class RedisKey {

    /**
     * 构建key
     *
     * @param prefix 前缀
     * @param suffix 后缀
     * @return {@code String}
     */
    public static String getKey(String prefix, String suffix) {
        return String.format("%s%s", prefix, suffix);
    }

}
