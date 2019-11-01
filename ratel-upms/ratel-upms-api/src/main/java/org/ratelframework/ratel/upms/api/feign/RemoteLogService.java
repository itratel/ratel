package org.ratelframework.ratel.upms.api.feign;


import org.ratelframework.ratel.upms.api.entity.SysLog;
import org.ratelframework.ratel.common.core.constant.SecurityConstants;
import org.ratelframework.ratel.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author whd.java@gmail.com
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteLogService {
	/**
	 * 保存日志
	 *
	 * @param sysLog 日志实体
	 * @param from   内部调用标志
	 * @return succes、false
	 */
	@PostMapping("/log")
	Response<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader(SecurityConstants.FROM) String from);
}
