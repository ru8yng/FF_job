package com.yyr.controller;

import account8001.dto.FamPermQueryForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.config.logCustom;
import com.yyr.pojo.FamPermission;
import com.yyr.service.FamPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.CommonResponse;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/1/28 14:32
 * @PackageName:com.yyr.controller
 * @ClassName: FamPermissionController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "家庭权限接口")
@RequestMapping("/famPerm")
@RestController
public class FamPermissionController {
    @Autowired
    private FamPermissionService famPermissionService;
    @ApiOperation("新增家庭权限")
    @logCustom(description = "新增家庭权限")
    @PostMapping("/addFamPerm")
    public CommonResponse<?> addFamPerm(@RequestBody FamPermQueryForm famPermission){
        Assert.notNull(famPermission,"家庭权限不能为空！");
        famPermissionService.addFamPermission(famPermission);
        return CommonResponse.ok("新增家庭权限成功！");
    }

    @ApiOperation("根据id删除家庭权限")
    @logCustom(description = "根据id删除家庭权限")
    @PostMapping("/deleteFamPerm")
    public CommonResponse<?> deleteFamPerm(@RequestBody String famPermId){
        Assert.notNull(famPermId,"家庭权限id不能为空！");
        famPermissionService.deleteFamPermission(famPermId);
        return CommonResponse.ok("删除家庭权限成功！");
    }

    @ApiOperation("更新家庭权限")
    @logCustom(description = "更新家庭权限")
    @PostMapping("/updateFamPerm")
    public CommonResponse<?> updateFamPerm(@RequestBody FamPermQueryForm famPermission){
        Assert.notNull(famPermission,"家庭权限不能为空！");
        famPermissionService.updateFamPermission(famPermission);
        return CommonResponse.ok("更新家庭权限成功！");
    }

    @ApiOperation("查询家庭权限列表")
    @PostMapping("/queryFamPermList")
    public CommonResponse<?> queryFamPermList(@RequestBody FamPermQueryForm form){
        //Assert.isTrue(form!=null,"form不能为空");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<FamPermission> list= famPermissionService.queryFamPermissionList(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }

}
