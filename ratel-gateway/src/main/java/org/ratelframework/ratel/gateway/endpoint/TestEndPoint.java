package org.ratelframework.ratel.gateway.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>TestEndPoint<p/>
 * TestEndPoint <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/12/1 11:07
 * @since 1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestEndPoint {


    @GetMapping("/hi")
    public String index(Principal principal) {
        return String.format("Hello, I'm %s", principal.getName());
    }

}
