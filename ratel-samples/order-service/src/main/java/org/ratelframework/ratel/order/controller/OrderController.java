package org.ratelframework.ratel.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author whd.java@gmail.com
 * @date 2019/05/11 00:11
 * @apiNote Describe the function of this class in one sentence
 */
@RestController
@RequiredArgsConstructor(onConstructor__={@Autowired})
public class OrderController {

    private final RestTemplate template;

    private final LoadBalancerClient loadBalancerClient;

    @SentinelResource("hello")
    @GetMapping("test-hello")
    public String testHello(String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-product-service");
        URI uri = serviceInstance.getUri();
        return template.getForObject(uri + "/hello?name=" + name, String.class);
    }
}
