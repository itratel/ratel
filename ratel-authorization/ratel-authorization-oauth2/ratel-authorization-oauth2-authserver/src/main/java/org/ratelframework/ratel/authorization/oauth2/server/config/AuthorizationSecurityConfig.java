package org.ratelframework.ratel.authorization.oauth2.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/24 23:37
 * @apiNote Describe the function of this class in one sentence
 */
@Component
public class AuthorizationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 拦截所有请求，使用httpBasic认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**")
                .fullyAuthenticated().and().httpBasic();
    }
}
