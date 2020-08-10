package org.ratelframework.ratel.common.core.annotation;

import java.lang.annotation.*;

/**
 * <p>注册中心服务器注解</p>
 * @author whd.java@gmail.com
 * @date 2019/10/22 15:03
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@RatelCloudApplication
public @interface EnableRegistryServer {
}
