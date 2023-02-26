package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import com.yyr.dto.CommonResponse;
import com.yyr.dto.LoginLogForm;
import com.yyr.dto.OperaLogForm;
import com.yyr.pojo.SysLoginLog;
import com.yyr.pojo.SysOperaLog;
import com.yyr.service.SysOperaLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/2/22 17:09
 * @PackageName:com.yyr.controller
 * @ClassName: SysOperaLogController
 * @Description: TODO
 * @Version 1.0
 */

@RestController
@RequestMapping("/sysOperaLog")
public class SysOperaLogController {
    @Autowired
    private SysOperaLogService sysOperaLogService;

    @ApiOperation("查询登录日志")
    @RequestMapping("/querySysLoginLog")
    public CommonResponse<?> querySysOperaLog(@RequestBody OperaLogForm form){
        Assert.notNull(form,"OperaLogForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<SysOperaLog> list=sysOperaLogService.querySysOperaLog(form);
        return CommonResponse.ok(new PageInfo<>(list));
    }

    @ApiOperation("新增登录日志")
    @RequestMapping("/addSysOperaLog")
    public CommonResponse<?> addSysOperaLog(@RequestBody OperaLogForm form){
        Assert.notNull(form,"OperaLogForm不能为空！");
       sysOperaLogService.addSysOperaLog(form);
        return CommonResponse.ok();
    }

}
