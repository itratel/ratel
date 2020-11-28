package org.ratelframework.ratel.authorization.oauth2.resource.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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


    @GetMapping("/{id}")
    public Map<String, String> read(@PathVariable Integer id, Principal principal) {
        Map<String, String> info = new HashMap<>(6);
        info.put("id", String.valueOf(id));
        info.put("resource", UUID.randomUUID().toString());
        info.put("user", principal.getName());
        return info;
    }
}