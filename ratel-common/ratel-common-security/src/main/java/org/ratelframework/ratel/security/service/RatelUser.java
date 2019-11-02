package org.ratelframework.ratel.security.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author whd.java@gmail.com
 * @date 2019/11/02 22:55
 * @apiNote Describe the function of this class in one sentence
 */
public class RatelUser extends User {

    /**
     * 用户ID
     */
    @Getter
    private Integer id;
    /**
     * 部门ID
     */
    @Getter
    private Integer deptId;


    public RatelUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public RatelUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
