package org.ratelframework.ratel.upms.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.common.core.utils.ResponseResult;
import org.ratelframework.ratel.security.utils.SecurityUtils;
import org.ratelframework.ratel.upms.api.entity.SysUser;
import org.ratelframework.ratel.upms.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor(onConstructor__={@Autowired})
@RequestMapping("/user")
public class SysUserController {


    private final ISysUserService userService;

    /**
     * 获取当前用户全部信息
     *
     * @return 用户信息
     */
    @GetMapping(value = {"/info"})
    public ResponseResult info() {
        String username = SecurityUtils.getUser().getUsername();
        SysUser user = userService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return ResponseResult.error("获取当前用户信息失败");
        }
        return null;
//        return ResponseResult.ok(userService.getUserInfo(user));
    }
}
