package org.ratelframework.ratel.logger.event;

import org.ratelframework.ratel.upms.api.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * Custom system log event
 * @author whd.java@gmail.com
 * @date 2019/11/1 15:22
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(SysLog source) {

		super(source);
	}
}
