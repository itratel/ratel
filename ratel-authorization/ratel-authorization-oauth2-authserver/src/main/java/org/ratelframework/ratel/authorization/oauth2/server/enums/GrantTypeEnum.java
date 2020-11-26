package org.ratelframework.ratel.authorization.oauth2.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/24 10:17
 * @apiNote Describe the function of this class in one sentence
 * @since 1.0.0
 */
@AllArgsConstructor
public enum GrantTypeEnum {
    /***
     * 授权码
     */
    AUTHORIZATION_CODE("authorization_code","授权码"),
    /***
     * 隐式许可
     */
    IMPLICIT("implicit", "隐式许可"),
    /***
     * 密码模式
     */
    PASSWORD("password", "资源所有者密码凭据"),
    /***
     * 客户端方式
     */
    CLIENT_CREDENTIALS("client_credentials", "客户端凭据"),

    /***
     * 刷新令牌
     */
    REFRESH_TOKEN("refresh_token", "刷新令牌"),
    ;

    @Getter
    private final String key;

    @Getter
    private final String value;

}
