package com.yyr.controller;

import com.yyr.config.logCustom;
import com.yyr.dto.CommonResponse;
import com.yyr.dto.ExpenseTypeForm;
import com.yyr.dto.FamIncomeForm;
import com.yyr.dto.IAE;
import com.yyr.service.FamExpenseService;
import com.yyr.service.FamIncomeService;
import com.yyr.service.IAEService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 杨亚茹
 * @Date 2023/3/28 15:40
 * @PackageName:com.yyr.controller
 * @ClassName: IncomeAndExpenseController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "收入支出类型接口")
@RequestMapping("/IAE")
@RestController
@Slf4j(topic = "IAE")
public class IncomeAndExpenseController {

    @Autowired
    private FamExpenseService famExpenseService;

    @Autowired
    private FamIncomeService famIncomeService;

    @Autowired
    private IAEService iaeService;

    @ApiOperation("查询本月收入支出")
    //@logCustom(description = "查询本月收入支出")
    @GetMapping("/queryIaeBCurrentMonth")
    public CommonResponse<?> queryIaeBCurrentMonth(){

        return CommonResponse.ok(iaeService.queryIaeBCurrentMonth());
    }
}
