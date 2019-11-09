package org.ratelframework.ratel.authorization.oauth2.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;


/**
 * @author whd.java@gmail.com
 * @date 2019/10/23 18:06
 * @version 0.0.1
 * @since 0.0.1
 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor(onConstructor__={@Autowired})
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
       return DataSourceBuilder.create().build();
    }

    /***
     * 配置appId和appSecret和callbackUrl
     * @param clients 客户端配置
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }

    /***
     * 认证服务端点配置
     * @param endpoints 端点
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
        endpoints.reuseRefreshTokens(true);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                //允许表单认证
                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
    }

    /***
     * <p>
     * Token存储可以采用JdbcTokenStore/RedisTokenStore/JwtTokenStore/JwkTokenStore
     * 这里我们采用JdbcTokenStore的方式进行。
     * </p>
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService(){
        return new JdbcClientDetailsService(dataSource());
    }

}
