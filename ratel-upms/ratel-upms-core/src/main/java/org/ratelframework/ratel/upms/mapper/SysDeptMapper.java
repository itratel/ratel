package org.ratelframework.ratel.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ratelframework.ratel.upms.api.entity.SysDept;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {


    /**
     * 关联dept——relation
     *
     * @return 数据列表
     */
    List<SysDept> listDepts();
}
