package com.zhn.gateway.filters;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.zhn.common.constants.TokenConstants;
import com.zhn.common.domain.User;
import com.zhn.common.utils.JwtUtils;
import com.zhn.common.utils.StringUtils;
import com.zhn.gateway.config.IgnoreWhiteConfig;
import com.zhn.gateway.utils.GatewayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class AuthFilters implements GlobalFilter, Ordered {

    @Autowired
    private IgnoreWhiteConfig ignoreWhitesConfig;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> whites = ignoreWhitesConfig.getWhites();
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if (StringUtils.matches(path, whites)) {
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst(TokenConstants.TOKEN);
        if (StringUtils.isEmpty(token)) {
            return GatewayUtils.errorResponse(exchange, "token不能为空！", HttpStatus.UNAUTHORIZED);
        }
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            return GatewayUtils.errorResponse(exchange, "token不合法！");
        }
        String userKey = JwtUtils.getUserKey(token);
        Boolean hasKey = redisTemplate.hasKey(TokenConstants.LOGIN_TOKEN_KEY + userKey);
        if (null == hasKey || !hasKey) {
            return GatewayUtils.errorResponse(exchange, "token过期！");
        }
        String jsonStr = redisTemplate.opsForValue().get(TokenConstants.LOGIN_TOKEN_KEY + userKey);
        User user = JSONObject.parseObject(jsonStr, User.class);
        Date lastLoginTime = user.getLastLoginTime();
        long between = DateUtil.between(lastLoginTime, new Date(), DateUnit.MINUTE);
        if (between >= 10) {
            redisTemplate.expire(TokenConstants.LOGIN_TOKEN_KEY + userKey, 15, TimeUnit.MINUTES);
        }
        // 验证通过放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
