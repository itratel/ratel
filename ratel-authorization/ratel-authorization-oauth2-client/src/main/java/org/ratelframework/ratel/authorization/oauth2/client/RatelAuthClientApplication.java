package org.ratelframework.ratel.authorization.oauth2.client;

import org.ratelframework.ratel.common.core.annotation.RatelCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * <p>RatelAuthClientApplication</p>
 * <p>
 *      验证认证客户端的服务
 * </p>
 * @author whd.java@gmail.com
 * @date 2019/10/25 9:32
 * @since 1.0.0
 */
@RatelCloudApplication
public class RatelAuthClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelAuthClientApplication.class, args);
    }
}
