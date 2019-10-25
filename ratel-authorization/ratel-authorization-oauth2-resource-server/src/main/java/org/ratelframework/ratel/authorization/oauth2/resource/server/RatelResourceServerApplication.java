package org.ratelframework.ratel.authorization.oauth2.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author whd.java@gmail.com
 * @version 0.0.1
 * @date 2019/10/25 10:31
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RatelResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelResourceServerApplication.class, args);
    }
}
