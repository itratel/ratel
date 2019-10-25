/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ratelframework.ratel.common.core.constant;

import lombok.experimental.UtilityClass;

/**
 * @author whd.java@gmail.com
 * @date 2019/2/1
 */
@UtilityClass
public class SecurityConstants {
	/**
	 * 角色前缀
	 */
	public final String ROLE = "ROLE_";
	/**
	 * 前缀
	 */
	public final String PROJECT_PREFIX = "pig_";

	/**
	 * oauth 相关前缀
	 */
	public final String OAUTH_PREFIX = "oauth:";
	/**
	 * 项目的license
	 */
	public final String PROJECT_LICENSE = "made by pig";

	/**
	 * 内部
	 */
	public final String FROM_IN = "Y";

	/**
	 * 标志
	 */
	public final String FROM = "from";

	/**
	 * 手机号登录URL
	 */
	public final String MOBILE_TOKEN_URL = "/mobile/token";

	/**
	 * 默认登录URL
	 */
	public final String OAUTH_TOKEN_URL = "/oauth/token";

	/**
	 * grant_type
	 */
	public final String REFRESH_TOKEN = "refresh_token";

	/**
	 * oauth 客户端信息
	 */
	public final String CLIENT_DETAILS_KEY = PROJECT_PREFIX + OAUTH_PREFIX + "client:details";

	/**
	 * {bcrypt} 加密的特征码
	 */
	public final String BCRYPT = "{bcrypt}";
	/**
	 * sys_oauth_client_details 表的字段，不包括client_id、client_secret
	 */
	public final String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
		+ "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
		+ "refresh_token_validity, additional_information, autoapprove";

	/**
	 * JdbcClientDetailsService 查询语句
	 */
	public final String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS
		+ " from sys_oauth_client_details";

	/**
	 * 默认的查询语句
	 */
	public final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

	/**
	 * 按条件client_id 查询
	 */
	public final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

	/***
	 * 资源服务器默认bean名称
	 */
	public final String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";
}
