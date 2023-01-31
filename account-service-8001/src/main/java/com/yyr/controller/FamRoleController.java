package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.dto.CommonResponse;
import com.yyr.dto.FamRoleQueryForm;
import com.yyr.pojo.FamPermission;
import com.yyr.pojo.FamilyRole;
import com.yyr.service.FamilyRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/1/28 14:33
 * @PackageName:com.yyr.controller
 * @ClassName: FamRoleController
 * @Description: TODO
 * @Version 1.0
 */

@RestController
@Api(tags="家庭角色接口")
@RequestMapping("/famRole")
public class FamRoleController {

    @Autowired
    private FamilyRoleService familyRoleService;
    @ApiOperation("新增家庭角色")
    @RequestMapping("/addFamRole")
    public CommonResponse<?> addFamRole(@RequestBody FamilyRole familyRole){
        Assert.notNull(familyRole,"新增的家庭角色不能为空！");
        familyRoleService.addFamRole(familyRole);
        return CommonResponse.ok("新增家庭角色成功！");
    }

    @ApiOperation("删除家庭角色")
    @RequestMapping("/deleteFamRole")
    public CommonResponse<?> deleteFamRole(@RequestBody String familyRoleId){
        Assert.notNull(familyRoleId,"删除家庭角色id不能为空！");
        familyRoleService.deleteFamRole(familyRoleId);
        return CommonResponse.ok("删除家庭角色成功！");
    }

    @ApiOperation("更新家庭角色")
    @RequestMapping("/updateFamRole")
    public CommonResponse<?> updateFamRole(@RequestBody FamilyRole familyRole){
        Assert.notNull(familyRole,"更新的家庭角色不能为空！");
        familyRoleService.updateFamRole(familyRole);
        return CommonResponse.ok("更新家庭角色成功！");
    }

    @ApiOperation("查询家庭角色列表")
    @RequestMapping("/queryFamRoleList")
    public CommonResponse<?> queryFamRoleList(@RequestBody FamRoleQueryForm form){
        Assert.isTrue(form!=null,"form不能为空");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<FamilyRole> list= familyRoleService.queryFamRoleList(form);
        return CommonResponse.ok(new PageInfo<>(list));
    }
}