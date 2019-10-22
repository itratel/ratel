package org.ratelframework.ratel.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author whd.java@gmail.com
 * @date 2019/5/10 18:11
 * @apiNote Describe the function of this class in one sentence
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderApplication {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
