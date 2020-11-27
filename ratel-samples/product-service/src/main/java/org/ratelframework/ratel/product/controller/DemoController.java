package org.ratelframework.ratel.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.ratelframework.ratel.upms.api.dto.UserInfo;
import org.ratelframework.ratel.upms.api.feign.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.ratelframework.ratel.common.core.utils.ResponseUtil.unwrap;

/**
 * @author whd.java@gmail.com
 * @date 2019/05/11 00:08
 * @apiNote Describe the function of this class in one sentence
 */
@Slf4j
@RestController
public class DemoController {

    private final RemoteUserService remoteUserService;

    @Autowired
    public DemoController(RemoteUserService remoteUserService) {
        this.remoteUserService = remoteUserService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name){
        UserInfo userInfo = unwrap(remoteUserService.info(name));
        log.info("{}的基本信息为： {}", name, userInfo);
        log.info("调用成功，调用参数为{}", name);
        return "hello" + name;
    }
}
