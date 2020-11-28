package org.ratelframework.ratel.authorization.oauth2.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * <p>ResourceServerConfig<p/>
 * ResourceServerConfig <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/11/28 21:57
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    /**
     * Use this to configure the access rules for secure resources. By default all resources <i>not</i> in "/oauth/**"
     * are protected (but no specific rules about scopes are given, for instance). You also get an
     * {@link OAuth2WebSecurityExpressionHandler} by default.
     *
     * @param http the current http filter configuration
     * @throws Exception if there is a problem
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/**").access("#oauth2.hasScope('all')")
            .anyRequest().permitAll()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Add resource-server specific properties (like a resource id). The defaults should work for many applications, but
     * you might want to change at least the resource id.
     *
     * @param resources configurer for the resource server
     * @throws Exception if there is a problem
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("res1")
                //token访问服务
                .tokenServices(tokenServices())
                //无状态的
                .stateless(true);
    }

    /***
     * 资源服务令牌解析服务
     * 用于向认证服务器验证token的合法性
     * @return {@link ResourceServerTokenServices}
     */
    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        //资源服务会调用授权认证服务的验证token的接口请求数据
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8082/authserver/oauth/check_token");
        remoteTokenServices.setClientId("client1");
        remoteTokenServices.setClientSecret("secret");
        return remoteTokenServices;
    }
}
