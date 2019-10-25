package org.ratelframework.ratel.authorization.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author whd.java@gmail.com
 * @version 0.0.1
 * @date 2019/10/25 9:32
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RatelAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelAuthServerApplication.class, args);
    }
}
