package org.ratelframework.ratel.logger.autoconfigure;


import lombok.AllArgsConstructor;
import org.ratelframework.ratel.logger.aspect.SysLogAspect;
import org.ratelframework.ratel.logger.event.SysLogListener;
import org.ratelframework.ratel.upms.api.feign.RemoteLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>
 *  System log auto configuration
 * </p>
 * @author whd.java@gmail.com
 * @date 2019/11/1 15:20
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
@AllArgsConstructor(onConstructor__= @Autowired)
@EnableFeignClients({"org.ratelframework.ratel.upms.api.feign"})
public class LogAutoConfiguration {

	private final RemoteLogService remoteLogService;

	@Bean
	public SysLogListener sysLogListener() {
		return new SysLogListener(remoteLogService);
	}

	@Bean
	public SysLogAspect sysLogAspect() {
		return new SysLogAspect();
	}
}
