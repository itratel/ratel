package org.ratelframework.ratel.upms.api.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.ratelframework.ratel.upms.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

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
	private String[] permissions;

	/**
	 * 角色集合
	 */
	private Integer[] roles;
}
