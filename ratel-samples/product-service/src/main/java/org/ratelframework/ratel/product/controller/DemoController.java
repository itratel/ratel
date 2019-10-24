package org.ratelframework.ratel.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whd.java@gmail.com
 * @date 2019/05/11 00:08
 * @apiNote Describe the function of this class in one sentence
 */
@Slf4j
@RestController
public class DemoController {


    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name){
        log.info("调用成功，调用参数为{}", name);
        return "hello" + name;
    }
}
