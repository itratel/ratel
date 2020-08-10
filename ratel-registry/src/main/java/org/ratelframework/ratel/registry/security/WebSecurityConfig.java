package org.ratelframework.ratel.registry.security;

import lombok.SneakyThrows;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/22 15:00
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest()
                .authenticated().and().httpBasic();
    }
}
