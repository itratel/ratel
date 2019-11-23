package org.ratelframework.ratel.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.ratelframework.ratel.common.core.utils.Response;
import org.ratelframework.ratel.upms.api.entity.SysMenu;
import org.ratelframework.ratel.upms.api.entity.SysRoleMenu;
import org.ratelframework.ratel.upms.api.vo.MenuVO;
import org.ratelframework.ratel.upms.mapper.SysMenuMapper;
import org.ratelframework.ratel.upms.mapper.SysRoleMenuMapper;
import org.ratelframework.ratel.upms.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
@Service
@AllArgsConstructor(onConstructor__={@Autowired} )
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {


    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Cacheable(value = "menu_details", key = "#roleId  + '_menu'")
    public List<MenuVO> getMenuByRoleId(Integer roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menu_details", allEntries = true)
    public Response removeMenuById(Integer id) {
        // 查询父节点为当前节点的节点
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query()
                .lambda().eq(SysMenu::getParentId, id));
        if (CollUtil.isNotEmpty(menuList)) {
            return Response.error("菜单含有下级不能删除");
        }

        sysRoleMenuMapper
                .delete(Wrappers.<SysRoleMenu>query()
                        .lambda().eq(SysRoleMenu::getMenuId, id));

        //删除当前菜单及其子菜单
        return Response.ok(this.removeById(id));
    }

    @Override
    @CacheEvict(value = "menu_details", allEntries = true)
    public Boolean updateMenuById(SysMenu sysMenu) {
        return this.updateById(sysMenu);
    }
}
