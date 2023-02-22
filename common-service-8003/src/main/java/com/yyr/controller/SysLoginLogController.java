package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.fastinfoset.CommonResourceBundle;
import com.yyr.Common_Service8003Application;
import com.yyr.dto.CommonResponse;
import com.yyr.dto.LoginLogForm;
import com.yyr.pojo.SysLoginLog;
import com.yyr.service.SysLoginLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/2/22 15:35
 * @PackageName:com.yyr.controller
 * @ClassName: SysLoginLogController
 * @Description: TODO
 * @Version 1.0
 */

@RestController
@RequestMapping("/sysLoginLog")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    @ApiOperation("查询登录日志")
    @RequestMapping("/querySysLoginLog")
    public CommonResponse<?> querySysLoginLog(@RequestBody LoginLogForm form){
        Assert.notNull(form,"SysLoginLog不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<SysLoginLog> list=sysLoginLogService.queryLogLoginList(form);
        return CommonResponse.ok(new PageInfo<>(list));
    }




}
