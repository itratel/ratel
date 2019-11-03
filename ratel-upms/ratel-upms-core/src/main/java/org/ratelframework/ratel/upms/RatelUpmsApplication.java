package org.ratelframework.ratel.upms;

import org.ratelframework.ratel.common.core.annotation.RatelCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * <p>
 *  基于RBAC的用户权限管理系统
 * </p>
 * @author whd.java@gmail.com
 * @date 2019/11/1 17:56
 * @version 0.0.1
 * @since 0.0.1
 */
@RatelCloudApplication
public class RatelUpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelUpmsApplication.class, args);
    }

}
