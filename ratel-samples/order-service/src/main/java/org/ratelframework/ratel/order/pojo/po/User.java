package org.ratelframework.ratel.order.pojo.po;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author whd.java@gmail.com
 * @date 2019/11/10 17:48
 * @apiNote Describe the function of this class in one sentence
 */
@Data
@ToString
@Accessors(chain = true)
public class User {
    private int id;
    private String username;
    private int gender;
    private String password;
}
