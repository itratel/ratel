package org.ratelframework.ratel.common.core.utils;

import lombok.*;
import lombok.experimental.Accessors;
import org.ratelframework.ratel.common.core.constant.CommonConstants;

import java.io.Serializable;


/**
 * @author whd.java@gmail.com
 * @date 2019/10/22 15:03
 * @apiNote Response Body
 * @since 0.0.1
 * @version 0.0.1
 */
@Builder
@ToString
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class Response<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private int code = CommonConstants.SUCCESS;

	private String msg = "success";

	private T data;

	public Response() {
		super();
	}

	public Response(T data) {
		super();
		this.data = data;
	}

	public Response(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}

	public Response(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = CommonConstants.FAIL;
	}
}
