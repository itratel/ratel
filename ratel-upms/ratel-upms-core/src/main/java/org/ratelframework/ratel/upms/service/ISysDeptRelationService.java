package org.ratelframework.ratel.upms.service;

import org.ratelframework.ratel.upms.api.entity.SysDept;
import org.ratelframework.ratel.upms.api.entity.SysDeptRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门关系表 服务类
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
public interface ISysDeptRelationService extends IService<SysDeptRelation> {

    /**
     * 新建部门关系
     *
     * @param sysDept 部门
     */
    void saveDeptRelation(SysDept sysDept);

    /**
     * 通过ID删除部门关系
     *
     * @param id
     */
    void removeDeptRelationById(Integer id);

    /**
     * 更新部门关系
     *
     * @param relation
     */
    void updateDeptRelation(SysDeptRelation relation);

}
