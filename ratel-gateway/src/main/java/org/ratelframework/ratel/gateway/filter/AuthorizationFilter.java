package org.ratelframework.ratel.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * <p>AuthorizationFilter<p/>
 *
 * @author whd.java@gmail.com
 * @date 2020/10/24 17:44
 * @since 1.0.0
 */
@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {

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
        //Get request url
        return chain.filter(exchange);
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
        return HIGHEST_PRECEDENCE + 10;
    }
}
