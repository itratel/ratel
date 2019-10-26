package org.ratelframework.ratel.authorization.oauth2.server.user;

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
public class RatelUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
