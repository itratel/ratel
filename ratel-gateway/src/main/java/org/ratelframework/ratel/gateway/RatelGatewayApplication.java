package org.ratelframework.ratel.gateway;

import org.ratelframework.ratel.common.core.annotation.RatelCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/24 13:25
 * @since 1.0.0
 */
@RatelCloudApplication
public class RatelGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelGatewayApplication.class, args);
    }
}
