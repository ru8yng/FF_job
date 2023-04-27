package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.dto.CommonResponse;
import com.yyr.dto.FamBudgetForm;
import com.yyr.pojo.FamBudget;
import com.yyr.service.FamBudgetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/24 14:05
 * @PackageName:com.yyr.controller
 * @ClassName: FamBudgetController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "家庭预算接口")
@RequestMapping("/famBudget")
@RestController
@Slf4j(topic = "famBudget")
public class FamBudgetController {

    @Autowired
    private FamBudgetService famBudgetService;


    @ApiOperation("新增家庭预算")
    //@logCustom(description = "新增家庭预算")
    @PostMapping("/addFamIncom")
    public CommonResponse<?> addFamBudget(@RequestBody FamBudgetForm form){
        Assert.notNull(form,"新增家庭预算不能为空！");
        famBudgetService.addFamBudget(form);
        return CommonResponse.ok("新增家庭预算成功！");
    }

    @ApiOperation("删除家庭预算")
    //@logCustom(description = "删除家庭预算")
    @PostMapping("/deleteFamBudget")
    public CommonResponse<?> deleteFamBudget(String famBudgetId){
        Assert.notNull(famBudgetId,"家庭预算id不能为空！");
        famBudgetService.deleteFamBudget(famBudgetId);
        return CommonResponse.ok("删除家庭预算成功！");
    }

    @ApiOperation("更新家庭预算")
    //@logCustom(description = "更新家庭预算")
    @PostMapping("/updateFamBudget")
    public CommonResponse<?> updateFamBudget(@RequestBody FamBudgetForm form){
        Assert.notNull(form,"更新的家庭预算不能为空！");
        famBudgetService.updateFamBudget(form);
        return CommonResponse.ok("更新家庭预算成功！");
    }


    @ApiOperation("查询家庭预算")
    @PostMapping("/queryFamBudget")
    public CommonResponse<?> queryFamBudget(@RequestBody FamBudgetForm form){
        Assert.notNull(form,"FamBudgetForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<FamBudget> list=famBudgetService.queryFamBudget(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }
    
}
