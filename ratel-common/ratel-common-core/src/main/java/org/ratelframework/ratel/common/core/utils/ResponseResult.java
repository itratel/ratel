package org.ratelframework.ratel.common.core.utils;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author whd.java@gmail.com
 * @version 0.0.1
 * @date 2019/10/22 15:03
 * @apiNote Response Body
 * @since 0.0.1
 */
@Builder
@ToString
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    /***
     * 错误信息方法
     * @param code 错误码
     * @param msg 消息
     * @return Response
     */
    public static <T> ResponseResult<T> error(int code, String msg) {
        return ResponseResult.from(code, msg, null);
    }


    /***
     * 操作成功信息方法
     * @return Response
     */
    public static <T> ResponseResult<T> ok() {
        return ResponseResult.from(200, "请求成功", null);
    }

    /***
     * 操作成功信息方法
     * @param result result to return
     * @return Response
     */
    public static <T> ResponseResult<T> ok(T result) {
        return ResponseResult.from(200, "请求成功", result);
    }

    /***
     * 操作成功信息方法
     * @param msg msg
     * @param result result
     * @return Response
     */
    public static <T> ResponseResult<T> ok(String msg, T result) {
        return ResponseResult.from(200, msg, result);
    }

    /***
     * @param code code
     * @param msg msg
     * @param result result
     * @return Response
     */
    public static <T> ResponseResult<T> from(int code, String msg, T result) {
        return new ResponseResult<>(code, msg, result);
    }

}
