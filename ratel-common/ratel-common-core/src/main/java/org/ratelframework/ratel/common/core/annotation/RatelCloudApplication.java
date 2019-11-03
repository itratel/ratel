package org.ratelframework.ratel.common.core.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.lang.annotation.*;

/**
 * @author whd.java@gmail.com
 * @version 0.0.1
 * @date 2019/10/22 15:03
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableDiscoveryClient
@SpringBootApplication
public @interface RatelCloudApplication {
}
