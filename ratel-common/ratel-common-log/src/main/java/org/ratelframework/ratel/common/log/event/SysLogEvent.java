package org.ratelframework.ratel.common.log.event;

import org.springframework.context.ApplicationEvent;
import org.ratelframework.ratel.admin.api.entity.SysLog;

/**
 * @author whd.java@gmail.com
 * 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(SysLog source) {

		super(source);
	}
}
