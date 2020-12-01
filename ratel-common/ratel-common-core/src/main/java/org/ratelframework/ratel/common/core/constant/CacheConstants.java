package org.ratelframework.ratel.common.core.constant;

import lombok.experimental.UtilityClass;

/**
 * <p>CacheConstants<p/>
 * CacheConstants <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/12/1 21:40
 * @since 1.0.0
 */
@UtilityClass
public class CacheConstants {

    /**
     * oauth 缓存前缀
     */
    public final String PROJECT_OAUTH_ACCESS = "ratel_oauth:access:";

    /**
     * oauth 缓存令牌前缀
     */
    public final String PROJECT_OAUTH_TOKEN = "ratel_oauth:token:";

    /**
     * 验证码前缀
     */
    public final String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY:";

    /**
     * 菜单信息缓存
     */
    public final String MENU_DETAILS = "menu_details";

    /**
     * 用户信息缓存
     */
    public final String USER_DETAILS = "user_details";

    /**
     * 字典信息缓存
     */
    public final String DICT_DETAILS = "dict_details";

    /**
     * oauth 客户端信息
     */
    public final String CLIENT_DETAILS_KEY = "ratel_oauth:client:details";

    /**
     * 参数缓存
     */
    public final String PARAMS_DETAILS = "params_details";
}
