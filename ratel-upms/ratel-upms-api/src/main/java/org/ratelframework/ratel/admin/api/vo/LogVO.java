package org.ratelframework.ratel.admin.api.vo;

import org.ratelframework.ratel.admin.api.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

/**
 * @author whd.java@gmail.com
 * @date 2019/2/1
 */
@Data
public class LogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private SysLog sysLog;
	private String username;
}
