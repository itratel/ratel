package org.ratelframework.ratel.authorization.oauth2.server.user;

import lombok.Setter;
import org.ratelframework.ratel.upms.api.dto.UserInfo;
import org.ratelframework.ratel.upms.api.entity.SysUser;
import org.ratelframework.ratel.upms.api.feign.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.ratelframework.ratel.common.core.utils.ResponseUtil.*;

/**
 * <p>RatelUserDetailServiceImpl</p>
 * 用来查询用户基本信息（用户名和密码以及权限和角色等基本信息）的服务实现类
 * @author whd.java@gmail.com
 * @date 2019/10/25 13:16
 * @since 1.0.0
 */
@Service
@Setter(onMethod__=@Autowired)
public class RatelUserDetailServiceImpl implements UserDetailsService {

    private RemoteUserService remoteUserService;

    /***
     * 通过用户名查询用户的详情信息
     * @param username username
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = unwrap(remoteUserService.info(username));
        if (userInfo == null) {
            return null;
        }
        SysUser sysUser = userInfo.getSysUser();
        return User.withUsername(sysUser.getUsername())
                .password(sysUser.getPassword()).authorities(userInfo.getPermissions()).build();
    }
}
