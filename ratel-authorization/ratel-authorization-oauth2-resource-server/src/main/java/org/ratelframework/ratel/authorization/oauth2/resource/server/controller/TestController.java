package org.ratelframework.ratel.authorization.oauth2.resource.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/25 00:23
 * @apiNote Describe the function of this class in one sentence
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/addTest")
    public String test(){
        return "test success";
    }
}