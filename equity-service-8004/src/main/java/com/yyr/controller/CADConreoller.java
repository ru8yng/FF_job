package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.pojo.ClaimsAndDebt;
import com.yyr.service.ClaimsAndDebtService;
import equity8004.dto.ClaimsAndDebtForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import utils.CommonResponse;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/7 22:13
 * @PackageName:com.yyr.controller
 * @ClassName: CADConreoller
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "借贷")
@RequestMapping("/CAD")
@RestController
@Slf4j(topic = "CAD")
public class CADConreoller {
    @Autowired
    private ClaimsAndDebtService claimsAndDebtService;

    @ApiOperation("新增借贷")
    //@logCustom(description = "新增借贷")
    @PostMapping("/addCAD")
    public CommonResponse<?> addCAD(@RequestBody ClaimsAndDebtForm form){
        Assert.notNull(form,"新增借贷不能为空！");
        claimsAndDebtService.addCAD(form);
        return CommonResponse.ok("新增借贷成功！");
    }

    @ApiOperation("删除借贷")
    //@logCustom(description = "删除借贷")
    @GetMapping("/deleteCAD/{assetsId}")
    public CommonResponse<?> deleteCAD(@PathVariable String assetsId){
        Assert.notNull(assetsId,"借贷id不能为空！");
        claimsAndDebtService.deleteCAD(assetsId);
        return CommonResponse.ok("删除借贷成功！");
    }

    @ApiOperation("更新借贷")
    //@logCustom(description = "更新借贷")
    @PostMapping("/updateCAD")
    public CommonResponse<?> updateCAD(@RequestBody ClaimsAndDebtForm form){
        Assert.notNull(form,"更新的借贷不能为空！");
        claimsAndDebtService.updateCAD(form);
        return CommonResponse.ok("更新借贷成功！");
    }


    @ApiOperation("查询借贷")
    @PostMapping("/queryCAD")
    public CommonResponse<?> queryCADe(@RequestBody ClaimsAndDebtForm form){
        Assert.notNull(form,"ClaimsAndDebtForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<ClaimsAndDebt> list=claimsAndDebtService.queryCAD(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }
}
