package com.yyr.controller;

import bills8002.dto.IAEForm;
import com.yyr.config.logCustom;
import com.yyr.service.FamExpenseService;
import com.yyr.service.FamIncomeService;
import com.yyr.service.IAEService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.CommonResponse;

/**
 * @Author 杨亚茹
 * @Date 2023/3/28 15:40
 * @PackageName:com.yyr.controller
 * @ClassName: IncomeAndExpenseController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "收入支出接口")
@RequestMapping("/IAE")
@RestController
@Slf4j(topic = "IAE")
public class IncomeAndExpenseController {



    @Autowired
    private IAEService iaeService;

    @ApiOperation("查询收入支出预算")
    //@logCustom(description = "查询收入支出预算")
    @PostMapping("/queryIaeCurrent")
    public CommonResponse<?> queryIaeCurrent(@RequestBody IAEForm iae){

        return CommonResponse.ok(iaeService.queryIaeCurrent(iae));
    }


    @ApiOperation("查询支出预算")
    //@logCustom(description = "查询支出预算")
    @PostMapping("/getExpenseBudgentLine")
    public CommonResponse<?> getExpenseBudgentLine(@RequestBody IAEForm iae){

        return CommonResponse.ok(iaeService.getExpenseBudgentLine(iae));
    }

    @ApiOperation("查询支出及类型")
    //@logCustom(description = "查询支出及类型")
    @PostMapping("/getExpenseAndType")
    public CommonResponse<?> getExpenseAndType(@RequestBody IAEForm iae){

        return CommonResponse.ok(iaeService.getExpenseAndType(iae));
    }

}
