package org.ratelframework.ratel.authorization.oauth2.server;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/29 22:16
 * @apiNote Describe the function of this class in one sentence
 */
public class PasswordEncoderTest {

    /***
     * 测试密码加密器
     */
    @Test
    public void testEncoder() {
        String encode = new BCryptPasswordEncoder().encode("secret");
        System.out.println("encode = " + encode);
    }
}
