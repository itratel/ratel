package org.ratelframework.ratel.upms.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ratelframework.ratel.upms.api.entity.SysUser;

import java.io.Serializable;
import java.util.List;

/**
 * <p>用户基本信息</p>
 * @author whd.java@gmail.com
 * @date 2019/2/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserInfo implements Serializable {
	/**
	 * 用户基本信息
	 */
	private SysUser sysUser;
	/**
	 * 权限标识集合
	 */
	private List<String> permissions;

	/**
	 * 角色集合
	 */
	private List<Integer> roles;
}
