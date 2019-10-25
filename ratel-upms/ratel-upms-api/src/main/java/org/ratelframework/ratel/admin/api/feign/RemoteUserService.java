package org.ratelframework.ratel.admin.api.feign;

import org.ratelframework.ratel.admin.api.dto.UserInfo;
import org.ratelframework.ratel.common.core.constant.SecurityConstants;
import org.ratelframework.ratel.common.core.constant.ServiceNameConstants;
import org.ratelframework.ratel.common.core.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author whd.java@gmail.com
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	Response<UserInfo> info(@PathVariable("username") String username
            , @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	Response<UserInfo> social(@PathVariable("inStr") String inStr);
}
