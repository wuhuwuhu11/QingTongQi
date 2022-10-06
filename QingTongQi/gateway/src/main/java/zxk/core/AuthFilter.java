package zxk.core;

import com.alibaba.fastjson.JSONObject;
import zxk.common.JwtUitl;
import zxk.common.ResultJson;
import zxk.common.WyException;
import zxk.entity.UmsUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Resource
    White white;
    @Resource(name = "redisTemplate")
    RedisTemplate<String, Object> redisTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 拿到request
        ServerHttpRequest request = exchange.getRequest();
        // 拿到response
        ServerHttpResponse response = exchange.getResponse();
        // 获取到当前的请求地址
        String requestUrl = request.getPath().value();
        // 当前请求地址 循环匹配白名单
        List<String> whiteUrls = white.getWhiteUrls();
        // 创建地址匹配工具
        AntPathMatcher pathMatcher = new AntPathMatcher();
        // 如果请求地址匹配到白名单的其中一个就可以直接通过
        for (String whiteUrl : whiteUrls) {
            if (pathMatcher.match(whiteUrl, requestUrl)) {
                return chain.filter(exchange);
            }
        }
        // 拿到token
        List<String> tokenList = request.getHeaders().get("token");
        if(tokenList == null || tokenList.size() == 0) {
            return error(response, ResultJson.unauthentication("未登录"));
        }
        String token = tokenList.get(0);
        // 验证token的合法性
        try {
            UmsUser umsUser = JwtUitl.decode(token);
            // 定义KEY
            String key = "ums::" + umsUser.getId() + "::session";
            // 如果缓存服务器中没有了key 说明超时了
            if(!redisTemplate.hasKey(key)) {
                return error(response, ResultJson.unauthentication("登录超时"));
            }
            // 更新失效时间
            redisTemplate.expire(key, 30, TimeUnit.MINUTES);
            String value = redisTemplate.opsForValue().get(key).toString();
            // 返回序列化
            List<String> backUrls = JSONObject.parseArray(value, String.class);
            // 循环比对 如果有一个 能匹配到请求地址 都可以通过
            for (String backUrl : backUrls) {
                if (pathMatcher.match(backUrl, requestUrl)) {
                    // 获取请求参数
                    URI uri = request.getURI();
                    // 拼接新的参数 openId
                    StringBuilder builder = new StringBuilder();
                    if(StringUtils.isNotBlank(uri.getRawQuery())) {
                        builder.append(uri.getRawQuery()).append("&");
                    }
                    builder.append("openId=" + umsUser.getId());
                    // 构建新uri 将参数替换
                    URI newuri = UriComponentsBuilder.fromUri(uri)
                            .replaceQuery(builder.toString())
                            .build(true)
                            .toUri();
                    // 构建新request 替换 uri
                    ServerHttpRequest newRequest = request.mutate().uri(newuri).build();
                    // 构建新exchange 替换 request
                    ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
                    return chain.filter(newExchange);
                }
            }
            return error(response, ResultJson.forbid());
        } catch (WyException e) {
            e.printStackTrace();
            return error(response, ResultJson.unauthentication("非法请求"));
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
    private Mono<Void> error (ServerHttpResponse response, ResultJson resultJson) {
        response.getHeaders().set("Content-Type", "application/json;charset=utf-8");
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(resultJson).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(dataBuffer));
    }
}
