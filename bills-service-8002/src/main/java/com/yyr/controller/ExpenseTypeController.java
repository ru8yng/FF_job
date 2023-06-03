package com.yyr.controller;

import bills8002.dto.ExpenseTypeForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.config.logCustom;
import com.yyr.pojo.ExpensesType;
import com.yyr.service.ExpensesTypeService;
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
 * @Date 2023/3/23 19:44
 * @PackageName:com.yyr.controller
 * @ClassName: ExpenseTypeController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "支出类型接口")
@RequestMapping("/ExpenseType")
@RestController
@Slf4j(topic = "ExpenseType")
public class ExpenseTypeController {
    @Autowired
    private ExpensesTypeService expensesTypeService;

    @ApiOperation("新增支出类型")
    @logCustom(description = "新增支出类型")
    @PostMapping("/addExpenseType")
    public CommonResponse<?> addExpenseType(@RequestBody ExpenseTypeForm form){
        Assert.notNull(form,"新增支出类型不能为空！");
        expensesTypeService.addExpensesType(form);
        return CommonResponse.ok("新增支出类型成功！");
    }

    @ApiOperation("删除支出类型")
    @logCustom(description = "删除支出类型")
    @GetMapping("/deleteExpenseType/{ExpenseTypeId}")
    public CommonResponse<?> deleteExpenseType(@PathVariable String ExpenseTypeId){
        Assert.notNull(ExpenseTypeId,"支出类型id不能为空！");
        expensesTypeService.deleteExpensesType(ExpenseTypeId);
        return CommonResponse.ok("删除支出类型成功！");
    }

    @ApiOperation("更新支出类型")
    @logCustom(description = "更新支出类型")
    @PostMapping("/updateExpenseType")
    public CommonResponse<?> updateExpenseType(@RequestBody ExpenseTypeForm form){
        Assert.notNull(form,"更新的支出类型不能为空！");
        expensesTypeService.updateExpensesType(form);
        return CommonResponse.ok("更新支出类型成功！");
    }


    @ApiOperation("查询支出类型")
    @PostMapping("/queryExpenseType")
    public CommonResponse<?> queryExpenseType(@RequestBody ExpenseTypeForm form){
        Assert.notNull(form,"ExpenseTypeForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<ExpensesType> list=expensesTypeService.queryExpensesTypes(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }
}
