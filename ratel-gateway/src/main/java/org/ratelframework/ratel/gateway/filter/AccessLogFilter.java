package org.ratelframework.ratel.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * <p>AccessLogFilter<p/>
 *
 * @author whd.java@gmail.com
 * @date 2020/10/26 10:43
 * @since 1.0.0
 */
@Slf4j
@Component
public class AccessLogFilter implements GlobalFilter, Ordered {

    /**
     * Process the Web request and (optionally) delegate to the next {@code WebFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().pathWithinApplication().value();
        HttpMethod method = request.getMethod();
        StringBuilder builder = new StringBuilder();
        URI targetUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        ServerHttpRequest serverHttpRequest = request.mutate().uri(request.getURI()).build();
        if (HttpMethod.GET.equals(method)) {
            MultiValueMap<String, String> queryParams = request.getQueryParams();
            try {
                builder.append(new ObjectMapper().writeValueAsString(queryParams));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
            }
        } else if (HttpMethod.POST.equals(method)) {
            Flux<DataBuffer> body = request.getBody();
            body.subscribe(dataBuffer -> {
                InputStream inputStream = dataBuffer.asInputStream();
                try {
                    builder.append(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            });
            request = new ServerHttpRequestDecorator(serverHttpRequest) {
                @Override
                public Flux<DataBuffer> getBody() {
                    final DataBufferFactory dataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
                    return Flux.just(dataBufferFactory.wrap(builder.toString().getBytes(StandardCharsets.UTF_8)));
                }
            };
        }

        InetSocketAddress remoteAddress = request.getRemoteAddress();
        return chain.filter(exchange.mutate().request(request).build()).then(Mono.fromRunnable(() -> {
            ServerHttpResponse response = exchange.getResponse();
            HttpStatus statusCode = response.getStatusCode();
            log.info("请求路径:{},远程IP地址:{},请求方法:{},请求参数:{},目标URI:{},响应码:{}",
                    path, remoteAddress, method, builder.toString(), targetUri, statusCode);
        }));
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
