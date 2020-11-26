package org.ratelframework.ratel.authorization.oauth2.server.handler;

import lombok.extern.slf4j.Slf4j;
import org.ratelframework.ratel.common.security.handler.AuthenticationSuccessEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * <p>RatelAuthenticationFailureEventHandler<p/>
 * RatelAuthenticationFailureEventHandler <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/11/26 23:12
 * @since 1.0.0
 */
@Slf4j
@Component
public class RatelAuthenticationSuccessEventHandler extends AuthenticationSuccessEventHandler {

    /**
     * 处理登录成功方法
     * <p>
     * 获取到登录的authentication 对象
     * @param authentication 登录对象
     */
    @Override
    public void handle(Authentication authentication) {
        log.info("用户：{} 登录成功", authentication.getPrincipal());
    }
}
