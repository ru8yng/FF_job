package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.dto.CommonResponse;
import com.yyr.dto.SysRoleQueryForm;
import com.yyr.pojo.SysRole;
import com.yyr.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @ApiOperation("新增系统角色")
    @PostMapping("/addSysRole")
    public CommonResponse<?> addSysRole(@RequestBody SysRole sysRole){
        Assert.notNull(sysRole,"新增角色不能为空！");
        sysRoleService.addSysRole(sysRole);
        return CommonResponse.ok("");

    }

    @ApiOperation("删除系统角色")
    @PostMapping("deleteSysRole")
    public CommonResponse<?> deleteSysRole(@RequestBody String id){
        Assert.notNull(id,"要删除的角色id不能为空！");
        sysRoleService.deleteSysRole(id);
        return CommonResponse.ok("删除成功！");

    }@ApiOperation("更新系统角色")
    @PostMapping("/updateSysRole")
    public CommonResponse<?> updateSysRole(@RequestBody SysRole sysRole){
        Assert.notNull(sysRole,"更新角色不能为空！");
        sysRoleService.updateSysRole(sysRole);
        return CommonResponse.ok("更新角色成功！");

    }@ApiOperation("查询系统角色")
    @PostMapping("/querySysRole")
    public CommonResponse<?> querySysRole(@RequestBody SysRoleQueryForm form){
        Assert.notNull(form,"SysRoleQueryForm不能为空！");

        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<SysRole> list=sysRoleService.querySysRole(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }
}
