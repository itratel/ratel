package org.ratelframework.ratel.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.ratelframework.ratel.order.feign.ProductFeignService.PRODUCT_SERVICE_NAME;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/24 13:49
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 */
@FeignClient(value = PRODUCT_SERVICE_NAME, path = "/product")
public interface ProductFeignService {

    String PRODUCT_SERVICE_NAME = "product-service";

    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);

}
