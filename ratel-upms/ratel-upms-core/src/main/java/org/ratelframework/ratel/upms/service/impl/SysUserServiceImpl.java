package org.ratelframework.ratel.upms.service.impl;

import org.ratelframework.ratel.upms.api.entity.SysUser;
import org.ratelframework.ratel.upms.mapper.SysUserMapper;
import org.ratelframework.ratel.upms.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
