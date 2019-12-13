package org.ratelframework.ratel.order;

import org.ratelframework.ratel.common.core.annotation.RatelCloudApplication;
import org.ratelframework.ratel.order.enable.BeanService;
import org.ratelframework.ratel.order.enable.EnableRatel;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author whd.java@gmail.com
 * @date 2019/5/10 18:11
 * @apiNote Describe the function of this class in one sentence
 */
@EnableRatel(basePackages = "org.ratelframework.ratel.order.enable.pojo", basePackageClasses = BeanService.class)
@EnableFeignClients
@RatelCloudApplication
public class OrderApplication {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
