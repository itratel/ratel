package org.ratelframework.ratel.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * <p>GatewayWebSecurityConfigurer<p/>
 * GatewayWebSecurityConfigurer <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/12/1 10:33
 * @since 1.0.0
 */
@Configuration
@EnableWebFluxSecurity
public class GatewayWebSecurityConfigurer {


    /**
     * The default {@link ServerHttpSecurity} configuration.
     * @param http http请求
     * @return {@link SecurityWebFilterChain}
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange()
                .anyExchange().authenticated()
                //option 请求默认放行
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .and()
                .formLogin();
        return http.build();
    }

}
