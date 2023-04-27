package com.yyr.controller;

import com.yyr.dto.BillsForm;
import com.yyr.dto.CommonResponse;
import com.yyr.service.CadAndAssetsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 杨亚茹
 * @Date 2023/4/24 17:28
 * @PackageName:com.yyr.controller
 * @ClassName: cadAndAssetsController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "bills类型接口")
@RequestMapping("/cada")
@RestController
@Slf4j(topic = "cada")
public class BillsController {

    @Autowired
    private CadAndAssetsService biils;

    @ApiOperation("查询bills")
    //@logCustom(description = "查询本月收入支出")
    @PostMapping("/queryBills")
    public CommonResponse<?> queryBills(@RequestBody BillsForm form){

        return CommonResponse.ok(biils.queryBills(form));
    }

}
