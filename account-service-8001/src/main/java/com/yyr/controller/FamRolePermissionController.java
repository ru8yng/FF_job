package com.yyr.controller;

import com.yyr.dto.CommonResponse;
import com.yyr.dto.FamRolePermissionForm;
import com.yyr.pojo.FamRolePermission;
import com.yyr.service.FamRolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 杨亚茹
 * @Date 2023/1/28 14:32
 * @PackageName:com.yyr.controller
 * @ClassName: FamRolePermissionController
 * @Description: TODO
 * @Version 1.0
 */

@RestController
@Api(tags="家庭角色权限接口")
@RequestMapping("/famRolePerm")
public class FamRolePermissionController {

    @Autowired
    private FamRolePermissionService famRolePermissionService;

    @ApiOperation("新增家庭角色权限")
    @RequestMapping("/addFamRole")
    public CommonResponse<?> addSysRole(@RequestBody FamRolePermissionForm form){
        Assert.notNull(form,"新增的家庭角色权限不能为空！");
        famRolePermissionService.addFamRolePermissionByFamRoleId(form);
        return CommonResponse.ok("新增家庭角色权限成功！");
    }
}
