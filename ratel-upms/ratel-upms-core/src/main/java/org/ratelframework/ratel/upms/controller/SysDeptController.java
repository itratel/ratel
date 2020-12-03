package org.ratelframework.ratel.upms.controller;


import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.common.core.utils.Response;
import org.ratelframework.ratel.logger.annotation.RatelLog;
import org.ratelframework.ratel.upms.api.dto.DeptTree;
import org.ratelframework.ratel.upms.api.entity.SysDept;
import org.ratelframework.ratel.upms.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor(onConstructor__={@Autowired})
public class SysDeptController {

    private final ISysDeptService sysDeptService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysDept
     */
    @GetMapping("/{id}")
    public Response<SysDept> getById(@PathVariable Integer id) {
        return Response.ok(sysDeptService.getById(id));
    }


    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    public Response<List<DeptTree>> listDeptTrees() {
        return Response.ok(sysDeptService.listDeptTrees());
    }

    /**
     * 返回当前用户树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/user-tree")
    public Response<List<DeptTree>> listCurrentUserDeptTrees() {
        return Response.ok(sysDeptService.listCurrentUserDeptTrees());
    }

    /**
     * 添加
     *
     * @param sysDept 实体
     * @return success/false
     */
    @RatelLog("添加部门")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_dept_add')")
    public Response<Boolean> save(@Valid @RequestBody SysDept sysDept) {
        return Response.ok(sysDeptService.saveDept(sysDept));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @RatelLog("删除部门")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_dept_del')")
    public Response<Boolean> removeById(@PathVariable Integer id) {
        return Response.ok(sysDeptService.removeDeptById(id));
    }

    /**
     * 编辑
     *
     * @param sysDept 实体
     * @return success/false
     */
    @RatelLog("编辑部门")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_dept_edit')")
    public Response<Boolean> update(@Valid @RequestBody SysDept sysDept) {
        sysDept.setUpdateTime(LocalDateTime.now());
        return Response.ok(sysDeptService.updateDeptById(sysDept));
    }
}
