package org.ratelframework.ratel.common.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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
    private final Integer id;
    /**
     * 部门ID
     */
    @Getter
    private final Integer deptId;


    public RatelUser(Integer id, Integer deptId, String username, String password, String ...authorityList) {
        super(username, password, AuthorityUtils.createAuthorityList(authorityList));
        this.id = id;
        this.deptId = deptId;
    }


    public RatelUser(Integer id, Integer deptId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.deptId = deptId;
    }

    public RatelUser(Integer id, Integer deptId, String username, String password, boolean enabled, boolean accountNonExpired,
                     boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
    }
}
