package com.yyr.controller;

import account8001.dto.FamRolePermissionForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.config.logCustom;
import com.yyr.pojo.FamRolePermission;
import com.yyr.service.FamRolePermissionService;
import com.yyr.service.FamilyRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import utils.CommonResponse;

import java.util.List;

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

    @Autowired
    private FamilyRoleService familyRoleService;

    @ApiOperation("新增家庭角色权限")
    @logCustom(description = "新增家庭角色权限")
    @PostMapping("/addFamRolePerm")
    public CommonResponse<?> addFamRolePerm(@RequestBody FamRolePermissionForm form){
        Assert.notNull(form,"新增的家庭角色权限不能为空！");
        famRolePermissionService.addFamRolePermissionByFamRoleId(form);
        return CommonResponse.ok("新增家庭角色权限成功！");
    }

    @ApiOperation("根据家庭角色id删除家庭角色权限")
    @logCustom(description = "根据家庭角色id删除家庭角色权限")
    @PostMapping("/deleteFamRolePerm")
    public CommonResponse<?> deleteFamRolePermissionByFamRoleId(@RequestBody FamRolePermissionForm form){
        Assert.notNull(form,"form不能为空！");
        famRolePermissionService.deleteFamRolePermissionByFamRolePermissionForm(form);
        return CommonResponse.ok("删除家庭角色权限成功！");
    }


//    @ApiOperation("根据家庭角色id更新家庭角色权限")
//    @logCustom(description = "根据家庭角色id更新家庭角色权限")
//    @PostMapping("/deleteFamRolePerm")
//    public CommonResponse<?> updateFamRolePermissionByFamRoleId(@RequestBody FamRolePermissionUpdateDto form){
//        Assert.notNull(form,"form不能为空！");
//        famRolePermissionService.updateByRoleId(form);
//        return CommonResponse.ok("更新家庭角色权限成功！");
//    }

    @ApiOperation("根据家庭角色id查询该角色权限")
    @PostMapping("/queryFamRolePerm")
    public CommonResponse<?> queryFamRolePermissionByFamRoleId(@RequestBody FamRolePermissionForm form){
        Assert.notNull(form.getFam_role_id(),"角色id不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<FamRolePermission> list=famRolePermissionService.queryFamRolePermissionByFamRoleId(form);
        return CommonResponse.ok(new PageInfo<>(list));
    }

    @ApiOperation("查询角色功能权限列表")
    @GetMapping("/queryFamPermissionListByRoleId")
    public CommonResponse<?> queryFamPermissionListByRoleId(String roleId) {
//        Assert.notNull(roleId,"角色id不能为空");
        return CommonResponse.ok(familyRoleService.queryFamPermissionByFamRoleId(roleId,true));
    }


}
