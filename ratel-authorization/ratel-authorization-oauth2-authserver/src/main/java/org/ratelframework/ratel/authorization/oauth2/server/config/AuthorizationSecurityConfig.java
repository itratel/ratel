package org.ratelframework.ratel.authorization.oauth2.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/24 23:37
 * @apiNote Describe the function of this class in one sentence
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class AuthorizationSecurityConfig extends WebSecurityConfigurerAdapter {

    /***
     * GET  http://localhost:8083/authserver/oauth/authorize?client_id=client_1&response_type=code
     */
    /***
     * POST http://client_1:123456@localhost:8083/authserver/oauth/token
     * grant_type: authorization_code
     * code: 2PeXZq
     */
    /***
     * 注册加密器
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /***
     * 内存保存账户信息/配置认证信息
     * @param auth auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("123456")).roles("USER");
    }
}
