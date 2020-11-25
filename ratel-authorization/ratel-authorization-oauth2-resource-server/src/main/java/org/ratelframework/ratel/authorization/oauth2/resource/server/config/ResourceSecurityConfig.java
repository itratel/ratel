package org.ratelframework.ratel.authorization.oauth2.resource.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>ResourceSecurityConfig<p/>
 * 资源服务器的安全配置 <br>
 * @author whd.java@gmail.com
 * @date 2020/11/25 20:37
 * @since 1.0.0
 */
@Configuration
@EnableGlobalMethodSecurity(proxyTargetClass = true, prePostEnabled = true)
public class ResourceSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 安全拦截机制
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
                //设置受保护资源的访问规则
                .authorizeRequests()
                //所有/test/**的请求必须要通过认证
                .antMatchers("/test/**").authenticated()
                //除了/test/**，其他的请求都可以访问
                .anyRequest().permitAll();
    }
}
