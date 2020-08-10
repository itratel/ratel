package org.ratelframework.ratel.authorization.oauth2.server;

import org.ratelframework.ratel.common.core.annotation.RatelCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/25 9:32
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 */
@RatelCloudApplication
@EnableFeignClients(basePackages = "org.ratelframework.ratel.upms.api")
public class RatelAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelAuthServerApplication.class, args);
    }
}
