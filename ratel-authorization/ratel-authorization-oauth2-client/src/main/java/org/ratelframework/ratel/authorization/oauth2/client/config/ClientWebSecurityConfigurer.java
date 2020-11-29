package org.ratelframework.ratel.authorization.oauth2.client.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>ClientWebSecurityConfigurer<p/>
 * ClientWebSecurityConfigurer <br>
 * 客户端安全配置
 * @author whd.java@gmail.com
 * @date 2020/11/29 0:17
 * @since 1.0.0
 */
@Configuration
@EnableOAuth2Sso
public class ClientWebSecurityConfigurer extends WebSecurityConfigurerAdapter {


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
                .anyRequest().authenticated();
    }
}
