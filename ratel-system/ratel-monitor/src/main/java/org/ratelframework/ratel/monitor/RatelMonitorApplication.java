package org.ratelframework.ratel.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.ratelframework.ratel.common.core.annotation.RatelCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * <p>微服务监控服务</p>
 * @author whd.java@gmail.com
 * @date 2019/10/23 11:59
 * @since 0.0.1
 */
@EnableAdminServer
@RatelCloudApplication
public class RatelMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelMonitorApplication.class, args);
    }
}
