package org.ratelframework.ratel.authorization.oauth2.resource.server;

import org.ratelframework.ratel.common.core.annotation.RatelCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/25 10:31
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 */
@RatelCloudApplication
public class RatelResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelResourceServerApplication.class, args);
    }
}
