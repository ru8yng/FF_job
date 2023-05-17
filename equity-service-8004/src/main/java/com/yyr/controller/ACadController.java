package com.yyr.controller;

import com.yyr.service.CadAndAssetsService;
import equity8004.dto.AcdaForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.CommonResponse;

/**
 * @Author 杨亚茹
 * @Date 2023/4/24 17:28
 * @PackageName:com.yyr.controller
 * @ClassName: cadAndAssetsController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "acad类型接口")
@RequestMapping("/acad")
@RestController
@Slf4j(topic = "acad")
public class ACadController {

    @Autowired
    private CadAndAssetsService acada;

    @ApiOperation("查询acad")
    //@logCustom(description = "查询本月收入支出")
    @PostMapping("/queryAcad")
    public CommonResponse<?> queryAcad(@RequestBody AcdaForm form){

        return CommonResponse.ok(acada.queryAcad(form));
    }

}
