package org.ratelframework.ratel.upms.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.common.core.constant.CommonConstants;
import org.ratelframework.ratel.common.core.utils.ResponseResult;
import org.ratelframework.ratel.common.log.annotation.SysLog;
import org.ratelframework.ratel.security.utils.SecurityUtils;
import org.ratelframework.ratel.upms.api.dto.MenuTree;
import org.ratelframework.ratel.upms.api.entity.SysMenu;
import org.ratelframework.ratel.upms.api.vo.MenuVO;
import org.ratelframework.ratel.upms.api.vo.TreeUtil;
import org.ratelframework.ratel.upms.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
@RestController
@RequiredArgsConstructor(onConstructor__={@Autowired})
@RequestMapping("/upms/menu")
public class SysMenuController {

    private final ISysMenuService sysMenuService;

    /**
     * 返回当前用户的树形菜单集合
     *
     * @return 当前用户的树形菜单
     */
    @GetMapping
    public ResponseResult getUserMenu() {
        // 获取符合条件的菜单
        Set<MenuVO> all = new HashSet<>();
        SecurityUtils.getRoles()
                .forEach(roleId -> all.addAll(sysMenuService.getMenuByRoleId(roleId)));
        List<MenuTree> menuTreeList = all.stream()
                .filter(menuVo -> CommonConstants.MENU.equals(menuVo.getType()))
                .map(MenuTree::new)
                .sorted(Comparator.comparingInt(MenuTree::getSort))
                .collect(Collectors.toList());
        return ResponseResult.ok(TreeUtil.buildByLoop(menuTreeList, -1));
    }

    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    public ResponseResult getTree() {
        return ResponseResult.ok(TreeUtil.buildTree(sysMenuService.list(Wrappers.emptyWrapper()), -1));
    }

    /**
     * 返回角色的菜单集合
     *
     * @param roleId 角色ID
     * @return 属性集合
     */
    @GetMapping("/tree/{roleId}")
    public List getRoleTree(@PathVariable Integer roleId) {
        return sysMenuService.getMenuByRoleId(roleId)
                .stream()
                .map(MenuVO::getMenuId)
                .collect(Collectors.toList());
    }

    /**
     * 通过ID查询菜单的详细信息
     *
     * @param id 菜单ID
     * @return 菜单详细信息
     */
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable Integer id) {
        return ResponseResult.ok(sysMenuService.getById(id));
    }

    /**
     * 新增菜单
     *
     * @param sysMenu 菜单信息
     * @return success/false
     */
    @SysLog("新增菜单")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_menu_add')")
    public ResponseResult save(@Valid @RequestBody SysMenu sysMenu) {
        return ResponseResult.ok(sysMenuService.save(sysMenu));
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return success/false
     */
    @SysLog("删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_menu_del')")
    public ResponseResult removeById(@PathVariable Integer id) {
        return sysMenuService.removeMenuById(id);
    }

    /**
     * 更新菜单
     *
     * @param sysMenu
     * @return
     */
    @SysLog("更新菜单")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_menu_edit')")
    public ResponseResult update(@Valid @RequestBody SysMenu sysMenu) {
        return ResponseResult.ok(sysMenuService.updateMenuById(sysMenu));
    }

}
