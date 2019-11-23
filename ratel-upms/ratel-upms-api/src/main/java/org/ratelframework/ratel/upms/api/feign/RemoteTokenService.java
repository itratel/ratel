package org.ratelframework.ratel.upms.api.feign;


import org.ratelframework.ratel.common.core.constant.SecurityConstants;
import org.ratelframework.ratel.common.core.constant.ServiceNameConstants;
import org.ratelframework.ratel.common.core.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author whd.java@gmail.com
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.AUTH_SERVICE)
public interface RemoteTokenService {
	/**
	 * 分页查询token 信息
	 *
	 * @param params 分页参数
	 * @param from   内部调用标志
	 * @return page
	 */
	@PostMapping("/token/page")
    Response getTokenPage(@RequestBody Map<String, Object> params, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 删除token
	 *
	 * @param token token
	 * @param from  调用标志
	 * @return
	 */
	@DeleteMapping("/token/{token}")
    Response<Boolean> removeToken(@PathVariable("token") String token, @RequestHeader(SecurityConstants.FROM) String from);
}
