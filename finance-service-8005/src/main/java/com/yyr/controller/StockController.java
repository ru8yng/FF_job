package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.dto.CommonResponse;
import com.yyr.dto.StockForm;
import com.yyr.dto.StockVS;
import com.yyr.dto.StockVSForm;
import com.yyr.pojo.Stock;
import com.yyr.service.StockService;
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
 * @Date 2023/4/11 14:52
 * @PackageName:com.yyr.controller
 * @ClassName: StockController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "股票接口")
@RequestMapping("/Stock")
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @ApiOperation("查询实时股票")
    //@logCustom(description = "新增股票类型")
    @PostMapping("/queryStockCurrent")
    public CommonResponse<?> queryStockCurrent(@RequestBody StockVSForm form){
        Assert.notNull(form,"查询股票不能为空！");
        StockVS vs=stockService.queryStockCurrent(form);
        return CommonResponse.ok(vs);
    }

    @ApiOperation("收藏股票")
    //@logCustom(description = "新增股票类型")
    @PostMapping("/collectStock")
    public CommonResponse<?> collectStock(@RequestBody StockVSForm form){
        Assert.notNull(form,"股票代码不能为空！");
        stockService.collectStock(form);
        return CommonResponse.ok("收藏成功！");
    }


    @ApiOperation("新增股票")
    //@logCustom(description = "新增股票类型")
    @PostMapping("/addStock")
    public CommonResponse<?> addStock(@RequestBody StockForm form){
        Assert.notNull(form,"新增股票不能为空！");
        stockService.addStock(form);
        return CommonResponse.ok("新增股票类型成功！");
    }

    @ApiOperation("删除股票")
    //@logCustom(description = "删除股票类型")
    @PostMapping("/deleteStock")
    public CommonResponse<?> deleteStock(String StockId){
        Assert.notNull(StockId,"股票id不能为空！");
        stockService.deleteStock(StockId);
        return CommonResponse.ok("删除股票成功！");
    }

    @ApiOperation("更新股票")
    //@logCustom(description = "更新股票类型")
    @PostMapping("/updateStock")
    public CommonResponse<?> updateStock(@RequestBody StockForm form){
        Assert.notNull(form,"更新的股票不能为空！");
        stockService.updateStock(form);
        return CommonResponse.ok("更新股票成功！");
    }


    @ApiOperation("查询股票")
    @PostMapping("/queryStock")
    public CommonResponse<?> queryStock(@RequestBody StockForm form){
        Assert.notNull(form,"StockForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<Stock> list=stockService.queryStock(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }
}
