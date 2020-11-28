package org.ratelframework.ratel.authorization.oauth2.resource;

import org.ratelframework.ratel.common.core.annotation.RatelCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * <p>RatelResourceServerApplication<p/>
 * RatelResourceServerApplication <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/11/28 20:56
 * @since 1.0.0
 */
@RatelCloudApplication
public class RatelResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelResourceServerApplication.class, args);
    }
}
