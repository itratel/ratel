package org.ratelframework.ratel.authorization.oauth2.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
     * 获取授权码的接口方法
     * GET  http://localhost:8082/authserver/oauth/authorize?client_id=client1&response_type=code
     * &scope-all&redirect_uri=http://www.baidu.com
     */

    /***
     * 获取token的接口方法
     * POST http://client1:123456@localhost:8082/authserver/oauth/token
     * client_id: client1
     * client_secret: secret
     * grant_type: authorization_code
     * code: 2PeXZq
     * redirect_uri：http://www.baidu.com
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
     * 认证管理器
     * @return {@link AuthenticationManager}
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/r/**").hasAnyAuthority("p1")
                .antMatchers("/login*").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }
}
