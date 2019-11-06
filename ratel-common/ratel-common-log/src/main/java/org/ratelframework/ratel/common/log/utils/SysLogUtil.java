package org.ratelframework.ratel.common.log.utils;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import lombok.experimental.UtilityClass;
import org.ratelframework.ratel.upms.api.entity.SysLog;
import org.ratelframework.ratel.common.core.constant.CommonConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * System log tools for get log info
 * @author whd.java@gamil.com
 * @date 2019/11/1 15:28
 */
@UtilityClass
public class SysLogUtil {

	/***
	 * Obtain system log info from client request
	 * @return {SysLog}
	 */
	public SysLog getSysLog() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
			.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		SysLog sysLog = new SysLog();
		sysLog.setCreateBy(Objects.requireNonNull(getUsername()));
		sysLog.setType(CommonConstants.STATUS_NORMAL);
		sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
		sysLog.setMethod(request.getMethod());
		sysLog.setUserAgent(request.getHeader("user-agent"));
		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
		sysLog.setServiceId(getClientId());
		return sysLog;
	}

	/**
	 * Obtain current client id
	 * @return clientId
	 */
	private String getClientId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
			return auth2Authentication.getOAuth2Request().getClientId();
		}
		return null;
	}

	/**
	 * Obtain current user name from {@link SecurityContextHolder}
	 * @return username
	 */
	private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) { return null; }
		return authentication.getName();
	}

}
