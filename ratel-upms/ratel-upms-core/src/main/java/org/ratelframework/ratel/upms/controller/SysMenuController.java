package org.ratelframework.ratel.upms.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/upms/sys-menu")
public class SysMenuController {

}
