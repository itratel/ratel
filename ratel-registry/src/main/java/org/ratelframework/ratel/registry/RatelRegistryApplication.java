package org.ratelframework.ratel.registry;

import org.ratelframework.ratel.registry.annotation.EnableRegistryServer;
import org.springframework.boot.SpringApplication;

/**
 * @author whd.java@gmail.com
 * @version 0.0.1
 * @date 2019/10/22 14:37
 * @apiNote Describe the function of this class in one sentence
 * <p>
 * {@link @SpringCloudApplication} is composition annotation that function be equivalent to three annotation as below
 * @see @SpringBootApplication
 * @see @EnableDiscoveryClient
 * @see @EnableCircuitBreaker
 * </p>
 * @since 0.0.1
 */
@EnableRegistryServer
public class RatelRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelRegistryApplication.class, args);
    }

}
