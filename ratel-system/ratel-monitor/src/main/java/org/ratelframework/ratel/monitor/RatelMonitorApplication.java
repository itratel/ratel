package org.ratelframework.ratel.monitor;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author whd.java@gmail.com
 * @version 0.0.1
 * @date 2019/10/23 11:59
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 */
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class RatelMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatelMonitorApplication.class, args);
    }
}
