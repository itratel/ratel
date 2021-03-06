package org.ratelframework.ratel.logger.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ratelframework.ratel.common.core.constant.SecurityConstants;
import org.ratelframework.ratel.upms.api.entity.SysLog;
import org.ratelframework.ratel.upms.api.feign.RemoteLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;


/**
 * <p>
 * Async listener for the type of {@link SysLogEvent}
 * </p>
 * @author whd.java@gmail.com
 * @date 2019/11/1 15:19
 */
@Slf4j
@AllArgsConstructor(onConstructor__={@Autowired})
public class SysLogListener {

	private final RemoteLogService remoteLogService;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {

		SysLog sysLog = (SysLog) event.getSource();
		remoteLogService.saveLog(sysLog, SecurityConstants.FROM_IN);
	}
}
