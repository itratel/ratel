package org.ratelframework.ratel.authorization.oauth2.server;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/29 22:16
 * @apiNote Describe the function of this class in one sentence
 */
public class PasswordEncoderTest {

    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println("encode = " + encode);
    }
}
