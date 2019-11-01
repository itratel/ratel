package org.ratelframework.ratel.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ratelframework.ratel.upms.api.entity.SysLog;
import org.ratelframework.ratel.upms.mapper.SysLogMapper;
import org.ratelframework.ratel.upms.service.ISysLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
