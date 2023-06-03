package com.yyr.controller;

import bills8002.dto.FamExpenseForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.config.logCustom;
import com.yyr.pojo.FamExpense;
import com.yyr.service.FamExpenseService;
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
 * @Date 2023/3/21 10:05
 * @PackageName:com.yyr.controller
 * @ClassName: famExpenseController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "家庭支出接口")
@RequestMapping("/famExpense")
@RestController
@Slf4j(topic = "famExpense")
public class famExpenseController {

    @Autowired
    private FamExpenseService famExpenseService;

    @ApiOperation("新增家庭支出")
    @logCustom(description = "新增家庭支出")
    @PostMapping("/addFamExpense")
    public CommonResponse<?> addFamExpense(@RequestBody FamExpenseForm form){
        Assert.notNull(form,"新增家庭支出不能为空！");
        famExpenseService.addFamExpense(form);
        return CommonResponse.ok("新增家庭支出成功！");
    }

    @ApiOperation("删除家庭支出")
    @logCustom(description = "删除家庭支出")
    @GetMapping("/deleteFamExpense/{famExpenseId}")
    public CommonResponse<?> deleteFamExpense(@PathVariable String famExpenseId){
        Assert.notNull(famExpenseId,"家庭支出id不能为空！");
        famExpenseService.deleteFamExpense(famExpenseId);
        return CommonResponse.ok("删除家庭支出成功！");
    }

    @ApiOperation("更新家庭支出")
    @logCustom(description = "更新家庭支出")
    @PostMapping("/updateFamExpense")
    public CommonResponse<?> updateFamExpense(@RequestBody FamExpenseForm form){
        Assert.notNull(form,"更新的家庭支出不能为空！");
        famExpenseService.updateFamExpense(form);
        return CommonResponse.ok("更新家庭支出成功！");
    }


    @ApiOperation("查询家庭支出")
    @PostMapping("/queryFamExpense")
    public CommonResponse<?> queryFamExpense(@RequestBody FamExpenseForm form){
        Assert.notNull(form,"FamExpenseForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<FamExpense> list=famExpenseService.queryFamExpense(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }

}
