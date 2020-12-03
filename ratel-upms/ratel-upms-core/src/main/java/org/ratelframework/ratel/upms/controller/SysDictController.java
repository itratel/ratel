package org.ratelframework.ratel.upms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.common.core.utils.Response;
import org.ratelframework.ratel.logger.annotation.RatelLog;
import org.ratelframework.ratel.upms.api.entity.SysDict;
import org.ratelframework.ratel.upms.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
@RestController
@RequestMapping("/dict")
@RequiredArgsConstructor(onConstructor__={@Autowired})
public class SysDictController {

    private final ISysDictService sysDictService;

    /**
     * 通过ID查询字典信息
     *
     * @param id ID
     * @return 字典信息
     */
    @GetMapping("/{id}")
    public Response<SysDict> getById(@PathVariable Integer id) {
        return Response.ok(sysDictService.getById(id));
    }

    /**
     * 分页查询字典信息
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Response<IPage<SysDict>> getDictPage(Page<SysDict> page, SysDict sysDict) {
        return Response.ok(sysDictService.page(page, Wrappers.query(sysDict)));
    }

    /**
     * 通过字典类型查找字典
     *
     * @param type 类型
     * @return 同类型字典
     */
    @GetMapping("/type/{type}")
    @Cacheable(value = "dict_details", key = "#type")
    public Response<List<SysDict>> getDictByType(@PathVariable String type) {
        return Response.ok(sysDictService.list(Wrappers
                .<SysDict>query().lambda()
                .eq(SysDict::getType, type)));
    }

    /**
     * 添加字典
     *
     * @param sysDict 字典信息
     * @return success、false
     */
    @RatelLog("添加字典")
    @PostMapping
    @CacheEvict(value = "dict_details", key = "#sysDict.type")
    @PreAuthorize("@pms.hasPermission('sys_dict_add')")
    public Response<Boolean> save(@Valid @RequestBody SysDict sysDict) {
        return Response.ok(sysDictService.save(sysDict));
    }

    /**
     * 删除字典，并且清除字典缓存
     *
     * @param id   ID
     * @param type 类型
     * @return Response
     */
    @RatelLog("删除字典")
    @DeleteMapping("/{id}/{type}")
    @CacheEvict(value = "dict_details", key = "#type")
    @PreAuthorize("@pms.hasPermission('sys_dict_del')")
    public Response<Boolean> removeById(@PathVariable Integer id, @PathVariable String type) {
        return Response.ok(sysDictService.removeById(id));
    }

    /**
     * 修改字典
     *
     * @param sysDict 字典信息
     * @return success/false
     */
    @PutMapping
    @RatelLog("修改字典")
    @CacheEvict(value = "dict_details", key = "#sysDict.type")
    @PreAuthorize("@pms.hasPermission('sys_dict_edit')")
    public Response<Boolean> updateById(@Valid @RequestBody SysDict sysDict) {
        return Response.ok(sysDictService.updateById(sysDict));
    }

}
