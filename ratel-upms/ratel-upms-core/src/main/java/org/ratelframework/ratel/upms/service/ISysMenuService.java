package org.ratelframework.ratel.upms.service;

import org.ratelframework.ratel.common.core.utils.Response;
import org.ratelframework.ratel.upms.api.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ratelframework.ratel.upms.api.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 通过角色编号查询URL 权限
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<MenuVO> getMenuByRoleId(Integer roleId);

    /**
     * 级联删除菜单
     *
     * @param id 菜单ID
     * @return 成功、失败
     */
    Response removeMenuById(Integer id);

    /**
     * 更新菜单信息
     *
     * @param sysMenu 菜单信息
     * @return 成功、失败
     */
    Boolean updateMenuById(SysMenu sysMenu);
}
