package org.ratelframework.ratel.common.core.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.lang.annotation.*;

/**
 * <p>Ratel微服务架构的组合注解，服务发现和Springboot启动注解</p>
 * @author whd.java@gmail.com
 * @date 2019/10/22 15:03
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableDiscoveryClient
@SpringBootApplication
public @interface RatelCloudApplication {
}
