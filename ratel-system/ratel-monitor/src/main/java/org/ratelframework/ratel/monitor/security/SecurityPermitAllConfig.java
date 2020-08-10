package org.ratelframework.ratel.monitor.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 微服务安全认证-开发环境
 * <p>
 * 微服务安全认证，主要用于在开发环境实现的监控服务，由于是开发环境，因此我们没有增加其他认证方式，
 * 其目的是为了让开发更加方便和高效。
 * </p>
 * @author whd.java@gmail.com
 * @date 2019/11/20 00:10
 * @since 0.0.1
 */
@Profile("dev")
@Configuration
public class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable();
    }
}