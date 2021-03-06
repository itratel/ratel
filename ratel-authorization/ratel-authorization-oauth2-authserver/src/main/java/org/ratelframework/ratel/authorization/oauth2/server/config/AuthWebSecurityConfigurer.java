package org.ratelframework.ratel.authorization.oauth2.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/24 23:37
 * @apiNote Describe the function of this class in one sentence
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor__=@Autowired)
public class AuthWebSecurityConfigurer extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;

    /***
     * 获取授权码的接口方法
     * GET  http://localhost:8082/sso/oauth/authorize?client_id=client1&response_type=code&scope=all&redirect_uri=http://www.baidu.com
     */

    /***
     * 获取token的接口方法
     * POST http://client1:123456@localhost:8082/sso/oauth/token
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
     * 注册认证管理器
     * @return {@link AuthenticationManager}
     * @throws Exception 注册认证管理器的异常信息
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses
     * should not invoke this method by calling super as it may override their
     * configuration. The default configuration is:
     *
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }


    /**
     * Used by the default implementation of {@link #authenticationManager()} to attempt
     * to obtain an {@link AuthenticationManager}. If overridden, the
     * {@link AuthenticationManagerBuilder} should be used to specify the
     * {@link AuthenticationManager}.
     *
     * <p>
     * The {@link #authenticationManagerBean()} method can be used to expose the resulting
     * {@link AuthenticationManager} as a Bean. The {@link #userDetailsServiceBean()} can
     * be used to expose the last populated {@link UserDetailsService} that is created
     * with the {@link AuthenticationManagerBuilder} as a Bean. The
     * {@link UserDetailsService} will also automatically be populated on
     * {@link HttpSecurity#getSharedObject(Class)} for use with other
     * {@link SecurityContextConfigurer} (i.e. RememberMeConfigurer )
     * </p>
     * @param auth the {@link AuthenticationManagerBuilder} to use
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
