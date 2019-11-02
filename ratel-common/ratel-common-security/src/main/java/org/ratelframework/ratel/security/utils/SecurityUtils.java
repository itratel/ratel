package org.ratelframework.ratel.security.utils;


import cn.hutool.core.util.StrUtil;

import lombok.experimental.UtilityClass;
import org.ratelframework.ratel.common.core.constant.SecurityConstants;
import org.ratelframework.ratel.security.service.RatelUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 安全工具类
 *
 * @author L.cm
 */
@UtilityClass
public class SecurityUtils {

	/**
	 * 获取Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 */
	public RatelUser getUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof RatelUser) {
			return (RatelUser) principal;
		}
		return null;
	}

	/**
	 * 获取用户
	 */
	public RatelUser getUser() {
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return null;
		}
		return getUser(authentication);
	}

	/**
	 * 获取用户角色信息
	 *
	 * @return 角色集合
	 */
	public List<Integer> getRoles() {
		Authentication authentication = getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<Integer> roleIds = new ArrayList<>();
		authorities.stream()
			.filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
			.forEach(granted -> {
				String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE);
				roleIds.add(Integer.parseInt(id));
			});
		return roleIds;
	}
}
