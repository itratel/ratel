package org.ratelframework.ratel.authorization.oauth2.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/29 22:16
 * @apiNote Describe the function of this class in one sentence
 */
@Slf4j
public class PasswordEncoderTest {

    /***
     * 测试密码加密器
     */
    @Test
    public void testEncoder() {
        String encode = new BCryptPasswordEncoder().encode("secret");
        System.out.println("encode = " + encode);
    }


    /***
     * 测试生成公司信息
     */
    @Test
    public void testGenCompanyInfo() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("appName", "平头哥科技有限公司");
        map.put("website", "http://www.itratel.com");
        String s = new ObjectMapper().writeValueAsString(map);
        log.info(s);
    }


}
