package org.ratelframework.ratel.authorization.oauth2.server.user;

import lombok.Setter;
import org.ratelframework.ratel.upms.api.feign.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/25 13:16
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 * @version 0.0.1
 */
@Service
@Setter(onMethod__={@Autowired})
public class RatelUserDetailService implements UserDetailsService {

    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
