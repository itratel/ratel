package org.ratelframework.ratel.upms.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019/2/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends Model<SysUserRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 角色ID
	 */
	private Integer roleId;

}
