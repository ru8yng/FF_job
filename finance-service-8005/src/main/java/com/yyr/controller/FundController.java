package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.service.FundService;
import finance8005.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import utils.CommonResponse;

import java.util.Date;
import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/3/28 9:58
 * @PackageName:com.yyr.controller
 * @ClassName: FundController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "基金接口")
@RequestMapping("/fund")
@RestController
public class FundController {

    @Autowired
    private FundService fundService;

    @ApiOperation("查询基金当前净值")
    //@logCustom(description = "新增基金类型")
    @GetMapping("/queryCurrentFundNetValueByCode/{fundCode}")
    public CommonResponse<?> queryCurrentFundNetValueByCode(@PathVariable String fundCode){
        Assert.notNull(fundCode,"基金代码不能为空！");
        CurrentFundNetValue value=fundService.queryCurrentFundNetValueByCode(fundCode);
        return CommonResponse.ok(value);
    }

    @ApiOperation("获取基金名称列表代码")
    //@logCustom(description = "新增基金类型")
    @GetMapping("/getFundcodeSearch")
    public CommonResponse<?> getFundcodeSearch(){
        List<FundcodeSearch> value=fundService.queryFundcodeSearch();
        return CommonResponse.ok(value);
    }

    @ApiOperation("获取历史基金净值获取")
    //@logCustom(description = "新增基金类型")
    @GetMapping("/getHistoricalFundNetValue")
    public CommonResponse<?> getHistoricalFundNetValue( String fundCode){
       HistoricalFundNetValue value=fundService.queryHistoricalFundNetValueByCode(fundCode);
        return CommonResponse.ok(value);
    }

    @ApiOperation("收藏基金")
    //@logCustom(description = "收藏基金")
    @PostMapping("/collectFund")
    public CommonResponse<?> addFucollectFundnd(@RequestBody FundCollectForm form){
        Assert.notNull(form,"基金代码不能为空！");
        fundService.collectFund(form);
        return CommonResponse.ok("收藏基金成功！");
    }


    @ApiOperation("新增基金")
    //@logCustom(description = "新增基金类型")
    @PostMapping("/addFund")
    public CommonResponse<?> addFund(@RequestBody FundForm form){
        Assert.notNull(form,"新增基金不能为空！");
        fundService.addFund(form);
        return CommonResponse.ok("新增基金成功！");
    }

    @ApiOperation("删除基金")
    //@logCustom(description = "删除基金类型")
    @GetMapping("/deleteFund/{fundId}")
    public CommonResponse<?> deleteFund(@PathVariable String fundId){
        Assert.notNull(fundId,"基金类型id不能为空！");
        fundService.deleteFund(fundId);
        return CommonResponse.ok("删除基金成功！");
    }

    @ApiOperation("更新基金")
    //@logCustom(description = "更新基金类型")
    @PostMapping("/updateFund")
    public CommonResponse<?> updateFund(@RequestBody FundForm form){
        Assert.notNull(form,"更新的基金不能为空！");
        fundService.updateFund(form);
        return CommonResponse.ok("更新基金成功！");
    }


    @ApiOperation("查询基金")
    @PostMapping("/queryFund")
    public CommonResponse<?> queryFund(@RequestBody FundForm form){
        Assert.notNull(form,"FundForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<FundForm> list=fundService.queryFund(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }

    @ApiOperation("获取日k线")
    @PostMapping("/getKLine")
    public CommonResponse<?> getKLine(@RequestBody Date date){
        Assert.notNull(date,"起始时间不能为空！");
        List<List<String>> list=fundService.getKLine(date);
        return CommonResponse.ok(list);

    }

}
