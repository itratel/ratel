package org.ratelframework.ratel.common.core.utils;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

/**
 * <p>ResponseUtil<p/>
 * ResponseUtil <br>
 * @author whd.java@gmail.com
 * @date 2020/11/25 21:53
 * @since 1.0.0
 */
public class ResponseUtil {

    /***
     * 打开包装获取真正的用户信息
     * @param response 响应结果
     * @param <T> 泛型类型
     * @return {@link T}
     */
    public static <T> T unwrap(Response<T> response) {
        Assert.notNull(response, "响应对象Response<T>不能为空");
        if (response.getCode() == HttpStatus.OK.value()) {
            return response.getData();
        }
        return null;
    }
}
