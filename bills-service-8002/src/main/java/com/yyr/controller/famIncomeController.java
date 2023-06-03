package com.yyr.controller;

import bills8002.dto.FamIncomeForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.config.logCustom;
import com.yyr.mapper.FamIncomeMapper;
import com.yyr.pojo.FamIncome;
import com.yyr.service.FamIncomeService;
import com.yyr.service.FamIncomeService;
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
 * @Date 2023/3/23 19:45
 * @PackageName:com.yyr.controller
 * @ClassName: famIncomeController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "家庭收入接口")
@RequestMapping("/famIncome")
@RestController
@Slf4j(topic = "famIncome")
public class famIncomeController {
    @Autowired
    private FamIncomeService famIncomeService;


    @ApiOperation("新增家庭收入")
    @logCustom(description = "新增家庭收入")
    @PostMapping("/addFamIncom")
    public CommonResponse<?> addFamIncom(@RequestBody FamIncomeForm form){
        Assert.notNull(form,"新增家庭收入不能为空！");
        famIncomeService.addFamIncome(form);
        return CommonResponse.ok("新增家庭收入成功！");
    }

    @ApiOperation("删除家庭收入")
    @logCustom(description = "删除家庭收入")
    @GetMapping("/deleteFamIncome/{famIncomeId}")
    public CommonResponse<?> deleteFamIncome(@PathVariable String famIncomeId){
        Assert.notNull(famIncomeId,"家庭收入id不能为空！");
        famIncomeService.deleteFamIncom(famIncomeId);
        return CommonResponse.ok("删除家庭收入成功！");
    }

    @ApiOperation("更新家庭收入")
    @logCustom(description = "更新家庭收入")
    @PostMapping("/updateFamIncome")
    public CommonResponse<?> updateFamIncome(@RequestBody FamIncomeForm form){
        Assert.notNull(form,"更新的家庭收入不能为空！");
        famIncomeService.updateFamIncome(form);
        return CommonResponse.ok("更新家庭收入成功！");
    }


    @ApiOperation("查询家庭收入")
    @PostMapping("/queryFamIncome")
    public CommonResponse<?> queryFamIncome(@RequestBody FamIncomeForm form){
        Assert.notNull(form,"FamIncomeForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<FamIncome> list=famIncomeService.queryFamIncome(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }
}
