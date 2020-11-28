package org.ratelframework.ratel.authorization.oauth2.client;

import org.ratelframework.ratel.common.core.annotation.RatelCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/25 9:32
 * @apiNote Describe the function of this class in one sentence
 * @since 1.0.0
 */
@RatelCloudApplication
public class RatelClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelClientApplication.class, args);
    }
}
