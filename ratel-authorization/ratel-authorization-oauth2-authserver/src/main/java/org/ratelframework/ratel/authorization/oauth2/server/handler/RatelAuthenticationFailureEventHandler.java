package org.ratelframework.ratel.authorization.oauth2.server.handler;

import lombok.extern.slf4j.Slf4j;
import org.ratelframework.ratel.common.security.handler.AuthenticationFailureEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
public class RatelAuthenticationFailureEventHandler extends AuthenticationFailureEventHandler {

	/**
	 * 处理登录失败方法
	 * <p>
	 * @param authenticationException 登录的authentication 对象
	 * @param authentication 登录的authenticationException 对象
	 */
	@Override
	public void handle(AuthenticationException authenticationException, Authentication authentication) {
		log.info("用户：{} 登录失败，异常：{}", authentication.getPrincipal(), authenticationException.getLocalizedMessage());
	}

}
