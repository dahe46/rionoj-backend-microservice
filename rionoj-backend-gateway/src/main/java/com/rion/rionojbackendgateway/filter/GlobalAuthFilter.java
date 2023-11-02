package com.rion.rionojbackendgateway.filter;

import cn.hutool.core.text.AntPathMatcher;
import com.rion.rionojbackendcommon.constant.HttpConstant;
import com.rion.rionojbackendcommon.constant.RedisConstant;
import com.rion.rionojbackendcommon.constant.RedisKey;
import com.rion.rionojbackendcommon.utils.JwtUtils;
import com.rion.rionojbackendcommon.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Order(-1)
public class GlobalAuthFilter implements GlobalFilter, Ordered {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    public static final List<String> WHITE_LIST = Arrays.asList("/api/user/login", "/api/user/register", "/api/user/get/login",
            "/api/question/question_submit/list/page", "/api/question/list/page/vo");
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if (antPathMatcher.match("/**/inner/**", path)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer dataBuffer = dataBufferFactory.wrap("No Auth".getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(dataBuffer));
        }
        System.out.println(path);
        if (antPathMatcher.match("/**/v2/api-docs/**", path)) {
            return chain.filter(exchange);
        }
        // 2.白名单放行
        if (WHITE_LIST.contains(path)) {
            return chain.filter(exchange);
        }
        // 取出token
        String token = request.getHeaders().getFirst(HttpConstant.TOKEN_HEADER_NAME);
        // 3.验证token
        if (token == null || token.isEmpty()) {
            return handleTokenValidationFailure(exchange, "Token is Null");
        }

        try {
            Long id = JwtUtils.getId(token);
            String key = RedisKey.getKey(RedisConstant.USER_TOKEN_KEY, id.toString());
            String redisToken = RedisUtils.get(key);
            if (!StringUtils.isNotEmpty(redisToken)) {
                return handleTokenValidationFailure(exchange, "未登录");
            }
            // Token续期
            Long expire = RedisUtils.getExpire(key, TimeUnit.MINUTES);
            // 若临近过期时间，则续期15分钟
            if (expire < RedisConstant.TOKEN_REMAINDER) {
                RedisUtils.setExpire(key, RedisConstant.TOKEN_RENEWAL_TIME, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            return handleTokenValidationFailure(exchange, "Token Validation Fail");
        }
        // 放行s
        return chain.filter(exchange);
    }

    // 处理Token验证失败
    private Mono<Void> handleTokenValidationFailure(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer dataBuffer = dataBufferFactory.wrap(message.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
