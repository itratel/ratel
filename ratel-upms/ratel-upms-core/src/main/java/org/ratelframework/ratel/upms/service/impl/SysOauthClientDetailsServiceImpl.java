package org.ratelframework.ratel.upms.service.impl;

import org.ratelframework.ratel.common.core.constant.CacheConstants;
import org.ratelframework.ratel.upms.api.entity.SysOauthClientDetails;
import org.ratelframework.ratel.upms.mapper.SysOauthClientDetailsMapper;
import org.ratelframework.ratel.upms.service.ISysOauthClientDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 终端信息表 服务实现类
 * </p>
 *
 * @author whd.java@gmail.com
 * @date 2020/11/21 15:53
 * @since 1.0.0
 */
@Service
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails> implements ISysOauthClientDetailsService {


    /**
     * 通过客户端id删除客户端
     *
     * @param id client id
     * @return {@link Boolean}
     */
    @Override
    @CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#id")
    public Boolean removeClientDetailsById(String id) {
        return this.removeById(id);
    }

    /**
     * 更新客户端信息
     *
     * @param clientDetails client info
     * @return {@link Boolean}
     */
    @Override
    @CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientDetails.clientId")
    public Boolean updateClientDetailsById(SysOauthClientDetails clientDetails) {
        return this.updateById(clientDetails);
    }
}
