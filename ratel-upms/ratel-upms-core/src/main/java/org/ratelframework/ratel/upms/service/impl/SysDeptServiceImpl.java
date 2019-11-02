package org.ratelframework.ratel.upms.service.impl;

import org.ratelframework.ratel.upms.api.dto.DeptTree;
import org.ratelframework.ratel.upms.api.entity.SysDept;
import org.ratelframework.ratel.upms.mapper.SysDeptMapper;
import org.ratelframework.ratel.upms.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {


    /**
     * 查询部门树菜单
     *
     * @return 树
     */
    @Override
    public List<DeptTree> listDeptTrees() {
        return null;
    }

    /**
     * 查询用户部门树
     *
     * @return
     */
    @Override
    public List<DeptTree> listCurrentUserDeptTrees() {
        return null;
    }

    /**
     * 添加信息部门
     *
     * @param sysDept
     * @return
     */
    @Override
    public Boolean saveDept(SysDept sysDept) {
        return null;
    }

    /**
     * 删除部门
     *
     * @param id 部门 ID
     * @return 成功、失败
     */
    @Override
    public Boolean removeDeptById(Integer id) {
        return null;
    }

    /**
     * 更新部门
     *
     * @param sysDept 部门信息
     * @return 成功、失败
     */
    @Override
    public Boolean updateDeptById(SysDept sysDept) {
        return null;
    }
}
