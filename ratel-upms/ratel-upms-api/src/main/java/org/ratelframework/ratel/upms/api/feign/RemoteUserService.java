package org.ratelframework.ratel.upms.api.feign;

import org.ratelframework.ratel.common.core.constant.ServiceNameConstants;
import org.ratelframework.ratel.common.core.utils.Response;
import org.ratelframework.ratel.upms.api.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author whd.java@gmail.com
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE, path = "/upms")
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @return {@link Response<UserInfo>}
	 */
	@GetMapping(value = "/user/info/{username}")
    Response<UserInfo> info(@PathVariable("username") String username);

	/**
	 * 通过社交账号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @return {@link Response<UserInfo>}
	 */
	@GetMapping("/social/info/{inStr}")
    Response<UserInfo> social(@PathVariable("inStr") String inStr);
}
