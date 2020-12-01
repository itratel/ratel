package org.ratelframework.ratel.upms;

import org.ratelframework.ratel.common.core.annotation.RatelCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * <p>
 *  基于RBAC的用户权限管理系统
 * </p>
 * @author whd.java@gmail.com
 * @date 2019/11/1 17:56
 * @since 1.0.0
 */
@EnableCaching
@RatelCloudApplication
public class RatelUpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelUpmsApplication.class, args);
    }

}
