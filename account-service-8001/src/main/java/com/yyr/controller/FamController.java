package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.dto.CommonResponse;
import com.yyr.dto.FamQueryForm;
import com.yyr.pojo.Family;
import com.yyr.service.FamilyService;
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
 * @Date 2023/1/28 14:33
 * @PackageName:com.yyr.controller
 * @ClassName: FamController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "家庭接口")
@RequestMapping("/fam")
@RestController
public class FamController {
    @Autowired
    private FamilyService familyService;

    @ApiOperation("新增家庭")
    @PostMapping("/addFam")
    public CommonResponse<?> addFam(@RequestBody Family family){
        Assert.notNull(family,"新增家庭不能为空！");
        familyService.addFamily(family);
        return CommonResponse.ok("新增家庭成功！");
    }

    @ApiOperation("删除家庭")
    @PostMapping("/deleteFam")
    public CommonResponse<?> deleteFam(String fmId){
        Assert.notNull(fmId,"家庭id不能为空！");
        familyService.deleteFamily(fmId);
        return CommonResponse.ok("删除家庭成功！");
    }

    @ApiOperation("更新家庭")
    @PostMapping("/updateFam")
    public CommonResponse<?> updateFam(@RequestBody Family fam){
        Assert.notNull(fam,"更新的家庭不能为空！");
        familyService.updateFamily(fam);
        return CommonResponse.ok("更新家庭成功！");
    }

    @ApiOperation("启停单个家庭状态")
    @PostMapping("/enableFam")
    public CommonResponse<?> enableFam(@RequestBody FamQueryForm form){
        Assert.notNull(form,"FamQueryForm不能为空！");
        familyService.enableFamily(form);
        return CommonResponse.ok("成功修改家庭状态为"+form.getStatus());
    }

    @ApiOperation("查询家庭")
    @PostMapping("/queryFamList")
    public CommonResponse<?> queryFamList(@RequestBody FamQueryForm form){
        Assert.notNull(form,"FamQueryForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<Family> list=familyService.queryList(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }
}