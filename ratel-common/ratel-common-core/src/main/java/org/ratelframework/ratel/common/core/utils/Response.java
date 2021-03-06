package org.ratelframework.ratel.common.core.utils;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author whd.java@gmail.com
 * @date 2019/10/22 15:03
 * @apiNote Response Body
 * @since 1.0.0
 */
@Data
@Builder
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    /***
     * 错误信息方法
     * @param msg 消息
     * @return Response
     */
    public static <T> Response<T> error(String msg) {
        return from (500, msg, null);
    }

    /***
     * 错误信息方法
     * @param exception 异常
     * @return Response
     */
    public static <T> Response<T> error(Exception exception) {
        return from (500, exception.getMessage(), null);
    }

    /***
     * 错误信息方法
     * @param code 错误码
     * @param msg 消息
     * @return Response
     */
    public static <T> Response<T> error(int code, String msg) {
        return from (code, msg, null);
    }


    /***
     * 操作成功信息方法
     * @return Response
     */
    public static <T> Response<T> ok() {
        return from (200, "请求成功", null);
    }

    /***
     * 操作成功信息方法
     * @param result result to return
     * @return Response
     */
    public static <T> Response<T> ok(T result) {
        return from (200, "请求成功", result);
    }

    /***
     * 操作成功信息方法
     * @param msg msg
     * @param result result
     * @return Response
     */
    public static <T> Response<T> ok(String msg, T result) {
        return from (200, msg, result);
    }

    /***
     * @param code code
     * @param msg msg
     * @param result result
     * @return Response
     */
    public static <T> Response<T> from(int code, String msg, T result) {
        return new Response<>(code, msg, result);
    }

}
