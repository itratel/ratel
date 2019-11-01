package org.ratelframework.ratel.common.log.event;

import org.springframework.context.ApplicationEvent;
import org.ratelframework.ratel.upms.api.entity.SysLog;

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
