package com.yyr.controller;

import bills8002.dto.IncomeTypeForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.config.logCustom;
import com.yyr.pojo.IncomeType;
import com.yyr.service.IncomeTypeService;
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
 * @ClassName: IncomeTypeController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "收入类型接口")
@RequestMapping("/IncomeType")
@RestController
@Slf4j(topic = "IncomeType")
public class IncomeTypeController {
    @Autowired
    private IncomeTypeService incomeTypeService;

    @ApiOperation("新增收入类型")
    //@logCustom(description = "新增收入类型")
    @PostMapping("/addIncomeType")
    public CommonResponse<?> addIncomeType(@RequestBody IncomeTypeForm form){
        Assert.notNull(form,"新增收入类型不能为空！");
        incomeTypeService.addIncomeType(form);
        return CommonResponse.ok("新增收入类型成功！");
    }

    @ApiOperation("删除收入类型")
    //@logCustom(description = "删除收入类型")
    @GetMapping("/deleteIncomeType/{IncomeTypeId}")
    public CommonResponse<?> deleteIncomeType(@PathVariable String IncomeTypeId){
        Assert.notNull(IncomeTypeId,"收入类型id不能为空！");
        incomeTypeService.deleteIncomeType(IncomeTypeId);
        return CommonResponse.ok("删除收入类型成功！");
    }

    @ApiOperation("更新收入类型")
    //@logCustom(description = "更新收入类型")
    @PostMapping("/updateIncomeType")
    public CommonResponse<?> updateIncomeType(@RequestBody IncomeTypeForm form){
        Assert.notNull(form,"更新的收入类型不能为空！");
        incomeTypeService.updateIncomeType(form);
        return CommonResponse.ok("更新收入类型成功！");
    }


    @ApiOperation("查询收入类型")
    @PostMapping("/queryIncomeType")
    public CommonResponse<?> queryIncomeType(@RequestBody IncomeTypeForm form){
        Assert.notNull(form,"IncomeTypeForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<IncomeType> list=incomeTypeService.queryIncomeType(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }
}
