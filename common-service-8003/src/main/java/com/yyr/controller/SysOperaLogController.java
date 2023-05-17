package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.pojo.SysOperaLog;
import com.yyr.service.SysOperaLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import log8003.dto.OperaLogForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import utils.CommonResponse;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/2/22 17:09
 * @PackageName:com.yyr.controller
 * @ClassName: SysOperaLogController
 * @Description: TODO
 * @Version 1.0
 */
@Api(tags = "操作日志接口")
@RestController
@RequestMapping("/sysOperaLog")
public class SysOperaLogController {
    @Autowired
    private SysOperaLogService sysOperaLogService;

    @ApiOperation("查询操作日志")
    @PostMapping("/querySysOperaLog")
    public CommonResponse<?> querySysOperaLog(@RequestBody OperaLogForm form){
        Assert.notNull(form,"OperaLogForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<SysOperaLog> list=sysOperaLogService.querySysOperaLog(form);
        return CommonResponse.ok(new PageInfo<>(list));
    }


    @ApiOperation("查询操作日志类型")
    @GetMapping("/querySysOperaLogTypes")
    public CommonResponse<?> querySysOperaLogTypes(){

        List<String> list=sysOperaLogService.querySysOperaLogType();
        return CommonResponse.ok(new PageInfo<>(list));
    }

    @ApiOperation("新增操作日志")
    @PostMapping("/addSysOperaLog")
    public CommonResponse<?> addSysOperaLog(@RequestBody OperaLogForm form){
        Assert.notNull(form,"OperaLogForm不能为空！");
       sysOperaLogService.addSysOperaLog(form);
        return CommonResponse.ok();
    }

}
