package org.ratelframework.ratel.common.security.annotation;

import java.lang.annotation.*;

/**
 * <p>CurRatelUser<p/>
 * 用来在参数上获取当前用户信息的注解 <br>
 * @author whd.java@gmail.com
 * @date 2020/11/26 11:28
 * @since 1.0.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CurRatelUser {

}
