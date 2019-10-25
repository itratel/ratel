package org.ratelframework.ratel.authorization.oauth2.resource.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/23 18:06
 * @version 0.0.1
 * @since 0.0.1
 * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer
 * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
//            .antMatchers("/product/**")
//            .access("#oauth2.hasScope('select') and hasPermission('delete')")
//            .antMatchers("/order/**")
            .antMatchers("/api/test/**")
                .authenticated();
    }
}