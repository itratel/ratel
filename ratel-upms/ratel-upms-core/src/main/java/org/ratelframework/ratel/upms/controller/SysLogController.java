package org.ratelframework.ratel.upms.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.common.core.utils.ResponseResult;
import org.ratelframework.ratel.upms.api.entity.SysLog;
import org.ratelframework.ratel.upms.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
@RestController
@RequestMapping("/upms/log")
@RequiredArgsConstructor(onConstructor__={@Autowired})
public class SysLogController {

    private final ISysLogService sysLogService;

    /**
     * 简单分页查询
     *
     * @param page   分页对象
     * @param sysLog 系统日志
     * @return
     */
    @GetMapping("/page")
    public ResponseResult getLogPage(Page page, SysLog sysLog) {
        return ResponseResult.ok(sysLogService.page(page, Wrappers.query(sysLog)));
    }

    /**
     * 删除日志
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_log_del')")
    public ResponseResult removeById(@PathVariable Long id) {
        return ResponseResult.ok(sysLogService.removeById(id));
    }

    /**
     * 插入日志
     *
     * @param sysLog 日志实体
     * @return success/false
     */
//    @Inner
    @PostMapping
    public ResponseResult save(@Valid @RequestBody SysLog sysLog) {
        return ResponseResult.ok(sysLogService.save(sysLog));
    }

}
