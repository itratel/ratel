package org.ratelframework.ratel.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * @author whd.java@gmail.com
 * @date 2018年06月22日16:22:03
 * 403 授权拒绝
 */
@NoArgsConstructor
public class RatelDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RatelDeniedException(String message) {
		super(message);
	}

	public RatelDeniedException(Throwable cause) {
		super(cause);
	}

	public RatelDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RatelDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
