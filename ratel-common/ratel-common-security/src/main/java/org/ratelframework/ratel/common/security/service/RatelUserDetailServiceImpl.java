package org.ratelframework.ratel.common.security.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.common.core.constant.CommonConstants;
import org.ratelframework.ratel.common.core.constant.SecurityConstants;
import org.ratelframework.ratel.common.security.model.RatelUser;
import org.ratelframework.ratel.upms.api.dto.UserInfo;
import org.ratelframework.ratel.upms.api.entity.SysUser;
import org.ratelframework.ratel.upms.api.feign.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.ratelframework.ratel.common.core.utils.ResponseUtil.unwrap;

/**
 * <p>RatelUserDetailServiceImpl</p>
 * 用来查询用户基本信息（用户名和密码以及权限和角色等基本信息）的服务实现类
 * @author whd.java@gmail.com
 * @date 2019/10/25 13:16
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor(onConstructor__=@Autowired)
public class RatelUserDetailServiceImpl implements UserDetailsService {

    private final RemoteUserService remoteUserService;

    /***
     * Query {@link UserDetails} by user name
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
        return getUserDetails(userInfo);
    }


    /**
     * Build {@link UserDetails} by given userInfo
     * @param userInfo user basic info
     * @return {@link UserDetails}
     */
    private UserDetails getUserDetails(UserInfo userInfo) {
        List<String> permissions = userInfo.getPermissions();
        List<Integer> roles = userInfo.getRoles();
        Set<String> authsSet = new HashSet<>();
        if (CollUtil.isNotEmpty(roles)) {
            // 获取角色
            roles.forEach(n -> authsSet.add(SecurityConstants.ROLE + n));
        }
        if (CollUtil.isNotEmpty(permissions)) {
            // 获取资源
            authsSet.addAll(permissions);
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authsSet.toArray(new String[0]));
        SysUser user = userInfo.getSysUser();
        // 构造security用户
        return new RatelUser(user.getUserId(), user.getDeptId(), user.getUsername(), user.getPassword(),
                StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL), true, true, true, authorities);
    }
}
