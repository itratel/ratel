package org.ratelframework.ratel.upms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.common.core.utils.Response;
import org.ratelframework.ratel.logger.annotation.RatelLog;
import org.ratelframework.ratel.upms.api.dto.UserInfo;
import org.ratelframework.ratel.upms.api.entity.SysUser;
import org.ratelframework.ratel.upms.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author whd.java@gmail.com
 * @since 2019-11-01
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor__=@Autowired)
public class SysUserController {

    private final ISysUserService userService;

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return {@link Response<UserInfo>}
     */
    @GetMapping(value = "/info/{username}")
    public Response<UserInfo> info(@PathVariable("username") String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers
                .<SysUser>lambdaQuery().eq(SysUser::getUsername, username);
        SysUser user = userService.getOne(queryWrapper);
        if (user == null) {
            return Response.ok();
        }
        //TODO 权限和角色列表还需完善
        UserInfo userInfo = UserInfo.of(user, null, null);
        return Response.ok(userInfo);
    }

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return {@link Response<SysUser>}
     */
    @GetMapping("/details/{username}")
    public Response<SysUser> user(@PathVariable String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username);
        return Response.ok(userService.getOne(queryWrapper));
    }

    /**
     * 删除用户信息
     * @param id ID
     * @return {@link Response<Boolean>}
     */
    @RatelLog("删除用户信息")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_user_del')")
    public Response<Boolean> userDel(@PathVariable Integer id) {
        SysUser sysUser = userService.getById(id);
        return Response.ok();
    }
}
