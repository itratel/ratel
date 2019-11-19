package org.ratelframework.ratel.monitor.security;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * 微服务安全认证-生产环境
 * <p>
 * 微服务安全认证，主要用于在生产环境实现的监控服务，因此增加了基于
 * httpBasic认证的授权方式，让我们的监控服务更加安全。
 * </p>
 * @author whd.java@gmail.com
 * @date 2019/11/20 00:10
 * @version 0.0.1
 * @since 0.0.1
 */
@Profile("prod")
@Configuration
public class SecurityMonitorConfig extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public SecurityMonitorConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");

        http.authorizeRequests()
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                .antMatchers(adminContextPath + "/actuator/health").permitAll()
                .antMatchers(adminContextPath + "/actuator").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler)
                .and()
                .logout().logoutUrl(adminContextPath + "/logout").and()
                .httpBasic().and()
                .csrf().disable();
    }
}