package org.ratelframework.ratel.common.log.autoconfigure;


import lombok.AllArgsConstructor;
import org.ratelframework.ratel.admin.api.feign.RemoteLogService;
import org.ratelframework.ratel.common.log.aspect.SysLogAspect;
import org.ratelframework.ratel.common.log.event.SysLogListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author whd.java@gmail.com
 * @date 2019/2/1
 * 日志自动配置
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
@EnableFeignClients({"org.ratelframework.ratel."})
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
