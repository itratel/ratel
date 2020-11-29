package org.ratelframework.ratel.authorization.oauth2.client.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>HomeController<p/>
 * HomeController <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/11/28 21:57
 * @since 1.0.0
 */
@RestController
public class HomeController {


    @GetMapping("/hi")
    public String index(Principal principal) {
        return String.format("Hello, I'm %s", principal.getName());
    }
}