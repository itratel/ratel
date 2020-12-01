package org.ratelframework.ratel.upms.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * SysOauthClientDetails
 * </p>
 * 客户端应用信息
 * @author whd.java@gmail.com
 * @since 2019/2/1
 */
@Data
@TableName(value = "oauth_client_details")
@EqualsAndHashCode(callSuper = true)
public class SysOauthClientDetails extends Model<SysOauthClientDetails> {

	private static final long serialVersionUID = 1L;

	/***
	 * 客户端id
	 */
	@TableId(value = "client_id", type = IdType.INPUT)
	private String clientId;

	/**
	 * 客户端密钥
	 */
	@TableField(value = "client_secret")
	private String clientSecret;

	/**
	 * 资源ID
	 */
	@TableField(value = "resource_ids")
	private String resourceIds;

	/**
	 * 作用域
	 */
	@TableField(value = "scope")
	private String scope;

	/**
	 * 授权方式（A,B,C）
	 */
	@TableField(value = "authorized_grant_types")
	private String authorizedGrantTypes;

	/**
	 * 回调地址
	 */
	@TableField(value = "web_server_redirect_uri")
	private String webServerRedirectUri;

	/**
	 * 权限
	 */
	@TableField(value = "authorities")
	private String authorities;

	/**
	 * 请求令牌有效时间
	 */
	@TableField(value = "access_token_validity")
	private Integer accessTokenValidity;

	/**
	 * 刷新令牌有效时间
	 */
	@TableField(value = "refresh_token_validity")
	private Integer refreshTokenValidity;

	/**
	 * 扩展信息
	 */
	@TableField(value = "additional_information")
	private String additionalInformation;

	/**
	 * 是否自动放行
	 */
	@TableField(value = "autoapprove")
	private String autoApprove;

}
