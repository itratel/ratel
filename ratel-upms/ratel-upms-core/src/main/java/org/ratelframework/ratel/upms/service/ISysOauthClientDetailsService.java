package org.ratelframework.ratel.upms.service;

import org.ratelframework.ratel.upms.api.entity.SysOauthClientDetails;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 客户端信息 服务类
 * </p>
 *
 * @author whd.java@gmail.com
 * @date 2020/11/21 15:53
 * @since 1.0.0
 */
public interface ISysOauthClientDetailsService extends IService<SysOauthClientDetails> {


    /**
     * 通过客户端id删除客户端
     * @param id client id
     * @return {@link Boolean}
     */
    Boolean removeClientDetailsById(String id);

    /**
     * 更新客户端信息
     * @param clientDetails client info
     * @return {@link Boolean}
     */
    Boolean updateClientDetailsById(SysOauthClientDetails clientDetails);

}
