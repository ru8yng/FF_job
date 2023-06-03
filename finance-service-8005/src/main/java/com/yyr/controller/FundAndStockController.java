package com.yyr.controller;

import com.yyr.config.logCustom;
import com.yyr.service.FundAndStockService;
import finance8005.dto.FundAndStockForm;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import utils.CommonResponse;

/**
 * @Author 杨亚茹
 * @Date 2023/4/29 14:31
 * @PackageName:com.yyr.controller
 * @ClassName: FundAndStockController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "获取基金股票当前收益")
@RestController
@RequestMapping("/FundAndStock")
public class FundAndStockController {

    @Autowired
    private FundAndStockService fundAndStockService;

    @PostMapping("/queryProfits")
    //@logCustom(description = "查询基金收益")
    public CommonResponse<?> queryProfits(@RequestBody FundAndStockForm form){
        Assert.notNull(form,"用户id不能为空！");
        return CommonResponse.ok(fundAndStockService.getFundAndStock(form));

    }



}
