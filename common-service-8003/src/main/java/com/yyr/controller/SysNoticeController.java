package com.yyr.controller;

import com.yyr.dto.CommonResponse;
import com.yyr.dto.SysNoticeForm;
import com.yyr.service.SysNoticeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 杨亚茹
 * @Date 2023/3/4 14:39
 * @PackageName:com.yyr.controller
 * @ClassName: SysNoticeController
 * @Description: TODO
 * @Version 1.0
 */


@Api(tags = "系统公告接口")
@RestController
@RequestMapping("/sysNotice")
public class SysNoticeController {

    private SysNoticeService service;

    @PostMapping("/addSysNotice")
    public CommonResponse<?> addSysNotice(@RequestBody SysNoticeForm form){

        return CommonResponse.ok();
    }

}
