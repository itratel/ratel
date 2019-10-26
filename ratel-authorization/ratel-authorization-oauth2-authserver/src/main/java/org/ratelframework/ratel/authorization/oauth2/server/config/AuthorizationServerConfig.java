package org.ratelframework.ratel.authorization.oauth2.server.config;

import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.authorization.oauth2.server.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

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
//@RequiredArgsConstructor(onConstructor__={@Autowired})
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /***
     * ACCESS_TOKEN过期时间，单位是秒
     */
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 7200;
    /***
     * REFRESH_TOKEN过期时间，单位是秒
     */
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 7200;

//    private final AuthenticationManager authenticationManager;
//
//    private final RedisConnectionFactory redisConnectionFactory;
//
//    private final UserDetailsService userDetailsService;

    /***
     * 配置appId和appSecret和callbackUrl
     * @param clients 客户端配置
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //如果需要合作机构需要做Oauth2认证的话，第一步操作是什么？
        //1.获取一个appId和appSecret,一般情况下是从数据库或者redis查询
        clients.inMemory()
                //某应用在此平台申请的client_id，相当于微信开发平台的开发接入应用的appId
                .withClient("client_1")
                //某平台在此平台上申请对应的secret，相当于微信开发平台的开发接入应用的appKey(appSecret)
                .secret("123456")
                //回调地址
                .redirectUris("http://www.itratel.com")
                //授权类型
                .authorizedGrantTypes(AUTHORIZATION_CODE.getValue(), PASSWORD.getValue(), REFRESH_TOKEN.getValue())
                //授权范围
                .scopes("all")
                //accessToken有效时间
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                //refreshToken有效时间
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    }

//    /***
//     * 认证服务端点配置
//     * @param endpoints 端点
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .tokenStore(new RedisTokenStore(redisConnectionFactory))
//                .tokenStore(new InMemoryTokenStore())
//                .tokenEnhancer(tokenEnhancer())

//                .authenticationManager(authenticationManager)
                //必须加上这个，否则刷新令牌会报错
//                .userDetailsService(userDetailsService)
                // 2018-4-3 增加配置，允许 GET、POST 请求获取 token，即访问端点：oauth/token
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

//        endpoints.reuseRefreshTokens(true);
//    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                //允许表单认证
                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
    }

//    @Bean
//    public TokenStore tokenStore() {
//        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        tokenStore.setPrefix(SecurityConstants.PROJECT_PREFIX + SecurityConstants.OAUTH_PREFIX);
//        return tokenStore;
//    }

//    @Bean
//    public TokenEnhancer tokenEnhancer() {
//        return (accessToken, authentication) -> {
//            final Map<String, Object> additionalInfo = new HashMap<>(1);
//            additionalInfo.put("license", SecurityConstants.PROJECT_LICENSE);
//            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//            return accessToken;
//        };
//    }

}
