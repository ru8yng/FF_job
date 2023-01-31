package com.yyr.controller;

import com.yyr.service.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 杨亚茹
 * @Date 2023/1/28 14:31
 * @PackageName:com.yyr.controller
 * @ClassName: SysRoleController
 * @Description: TODO
 * @Version 1.0
 */

@RestController
@Api(tags="系统角色接口")
@RequestMapping("/SysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    
}
