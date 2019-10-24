package org.ratelframework.ratel.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/24 13:25
 * @apiNote Describe the function of this class in one sentence
 * @version 0.0.1
 * @since 0.0.1
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RatelGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelGateWayApplication.class, args);
    }
}
