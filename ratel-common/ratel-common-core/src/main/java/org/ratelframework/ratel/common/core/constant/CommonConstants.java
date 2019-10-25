package org.ratelframework.ratel.common.core.constant;

import lombok.experimental.UtilityClass;

/**
 * @author whd.java@gmail.com
 * @date 2019/2/1
 */
@UtilityClass
public class CommonConstants {
	/**
	 * 删除
	 */
	public final String STATUS_DEL = "1";
	/**
	 * 正常
	 */
	public final String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	public final String STATUS_LOCK = "9";

	/**
	 * 菜单
	 */
	public final String MENU = "0";

	/**
	 * 编码
	 */
	public final String UTF8 = "UTF-8";

	/**
	 * JSON 资源
	 */
	public final String CONTENT_TYPE = "application/json; charset=utf-8";

	/**
	 * 前端工程名
	 */
	public final String FRONT_END_PROJECT = "ratel-ui";

	/**
	 * 后端工程名
	 */
	public final String BACK_END_PROJECT = "ratel";

	/**
	 * 成功标记
	 */
	public Integer SUCCESS = 0;
	/**
	 * 失败标记
	 */
	public Integer FAIL = 1;

	/**
	 * 验证码前缀
	 */
	public final String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";
}
