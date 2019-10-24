package org.ratelframework.ratel.authorization.oauth2.resource.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

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

    private static final String DEMO_RESOURCE_ID = "order";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .anonymous()
            .and()
            .authorizeRequests()
            .antMatchers("/product/**")
            .access("#oauth2.hasScope('select') and hasPermission('delete')")
            .antMatchers("/order/**").authenticated();
    }
}