package org.ratelframework.ratel.authorization.oauth2.server.config;

import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.authorization.oauth2.server.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.nimbusds.oauth2.sdk.GrantType.*;


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

    /***
     * ACCESS_TOKEN过期时间，单位是秒
     */
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 7200;
    /***
     * REFRESH_TOKEN过期时间，单位是秒
     */
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 7200;

    private final BCryptPasswordEncoder passwordEncoder;

//    private final AuthenticationManager authenticationManager;
//
//    private final RedisConnectionFactory redisConnectionFactory;
//
//    private final UserDetailsService userDetailsService;

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
//                .tokenEnhancer(tokenEnhancer())
//                .authenticationManager(authenticationManager)
//                必须加上这个，否则刷新令牌会报错
//                .userDetailsService(userDetailsService)
//                 2018-4-3 增加配置，允许 GET、POST 请求获取 token，即访问端点：oauth/token
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
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

//    @Bean
//    public TokenEnhancer tokenEnhancer() {
//        return (accessToken, authentication) -> {
//            final Map<String, Object> additionalInfo = new HashMap<>(1);
//            additionalInfo.put("license", SecurityConstants.PROJECT_LICENSE);
//            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//            return accessToken;
//        };
//    }



//    @Bean
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource());
//        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        tokenStore.setPrefix(SecurityConstants.PROJECT_PREFIX + SecurityConstants.OAUTH_PREFIX);
//        return tokenStore;
//    }

}
