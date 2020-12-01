package org.ratelframework.ratel.upms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.common.core.utils.Response;
import org.ratelframework.ratel.logger.annotation.RatelLog;
import org.ratelframework.ratel.upms.api.entity.SysOauthClientDetails;
import org.ratelframework.ratel.upms.service.ISysOauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 客户端信息 前端控制器
 * </p>
 *
 * @author whd.java@gmail.com
 * @date 2020/11/21 15:53
 * @since 1.0.0
 */
@RestController
@RequestMapping("/oauth-client-details")
@RequiredArgsConstructor(onConstructor__=@Autowired)
public class SysOauthClientDetailsController {

    private final ISysOauthClientDetailsService oauthClientDetailsService;

    /**
     * 通过ID查询
     * @param id clientId
     * @return SysOauthClientDetails
     */
    @GetMapping("/{id}")
    public Response<SysOauthClientDetails> getById(@PathVariable String id) {
        return Response.ok(oauthClientDetailsService.getById(id));
    }

    /**
     * 简单分页查询
     * @param page 分页对象
     * @param sysOauthClientDetails 系统客户端
     * @return {@link Response<IPage<SysOauthClientDetails>>}
     */
    @GetMapping("/page")
    public Response<IPage<SysOauthClientDetails>> getOauthClientDetailsPage(Page<SysOauthClientDetails> page, SysOauthClientDetails sysOauthClientDetails) {
        return Response.ok(oauthClientDetailsService.page(page, Wrappers.query(sysOauthClientDetails)));
    }

    /**
     * 添加
     * @param sysOauthClientDetails 实体
     * @return success/false
     */
    @RatelLog("添加客户端")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_client_add')")
    public Response<Boolean> add(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
        return Response.ok(oauthClientDetailsService.save(sysOauthClientDetails));
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @RatelLog("删除客户端")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_client_del')")
    public Response<Boolean> removeById(@PathVariable String id) {
        return Response.ok(oauthClientDetailsService.removeClientDetailsById(id));
    }

    /**
     * 编辑
     * @param sysOauthClientDetails 实体
     * @return success/false
     */
    @RatelLog("编辑客户端")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_client_edit')")
    public Response<Boolean> update(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
        return Response.ok(oauthClientDetailsService.updateClientDetailsById(sysOauthClientDetails));
    }
}
