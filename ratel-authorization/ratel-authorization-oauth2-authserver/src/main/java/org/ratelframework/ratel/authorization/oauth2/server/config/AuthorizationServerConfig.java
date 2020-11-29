package org.ratelframework.ratel.authorization.oauth2.server.config;

import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.common.security.service.RatelClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/23 18:06
 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer
 * @since 1.0.0
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor(onConstructor__ = @Autowired)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final DataSource dataSource;

    /***
     * 配置appId和appSecret和callbackUrl
     * 用来配置客户端详情服务，客户端详情信息在这里初始化
     * 你可以把客户端信息写死或者通过数据库来存储和调用详情信息
     * @param clients 客户端配置
     * @throws Exception Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /***
     * 认证服务端点配置
     * 用来配置令牌的访问端点和令牌服务（token services）
     * @param endpoints 端点
     * @throws Exception Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //密码模式 必须要的配置
                .authenticationManager(authenticationManager)
                //授权码模式需要的配置
                .authorizationCodeServices(authorizationCodeServices())
                //令牌管理的服务，不管什么模式都需要
                .tokenServices(tokenServices())
                //客户端信息
                .userDetailsService(userDetailsService)
                //自定义token校验页面
                .pathMapping("/oauth/confirm_access", "/token/confirm_access")
                //允许post方式访问令牌
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET);
    }

    /***
     * 用来配置令牌端点的安全策略
     * @param security security
     * @throws Exception Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //端点/oauth/token_key完全公开
                .tokenKeyAccess("permitAll()")
                //端点/oauth/check_token完全公开
                .checkTokenAccess("permitAll()")
                //允许表单认证来申请令牌
                .allowFormAuthenticationForClients();
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
        //使用内存来存储令牌（普通令牌）
        return new JdbcTokenStore(dataSource);
    }

    /***
     * 令牌管理服务
     * 用来从创建令牌，获取令牌以及刷新令牌的服务
     * @return {@link AuthorizationServerTokenServices}
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //客户端详情服务
        tokenServices.setClientDetailsService(clientDetailsService());
        //是否产生刷新令牌
        tokenServices.setSupportRefreshToken(true);
        //令牌存储策略
        tokenServices.setTokenStore(tokenStore());
        //令牌默认有效期2小时
        tokenServices.setAccessTokenValiditySeconds(7200);
        //刷新令牌默认有效期三天
        tokenServices.setRefreshTokenValiditySeconds(259200);
        //token增强
        tokenServices.setTokenEnhancer((accessToken, authentication) -> {
            //TODO token增强
            return accessToken;
        });
        return tokenServices;
    }

    /***
     * Services for issuing and storing authorization codes.
     * 用于发出和存储【授权代码】的服务
     * @return {@link AuthorizationCodeServices}
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /***
     * 注册客户端详情服务
     * @return {@link ClientDetailsService}
     */
    @Bean
    public ClientDetailsService clientDetailsService() {
        RatelClientDetailsService clientDetailsService = new RatelClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

}
