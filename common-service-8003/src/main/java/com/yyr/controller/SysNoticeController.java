package com.yyr.controller;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.pojo.SysNotice;
import com.yyr.service.SysNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import log8003.dto.SysNoticeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import utils.CommonResponse;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private SysNoticeService service;

    @ApiOperation("新增系统公告")
    @PostMapping("/addSysNotice")
    public CommonResponse<?> addSysNotice(@RequestBody SysNoticeForm form){
        Assert.notNull(form,"新增系统公告不能为空！");
        service.addNotice(form);
        return CommonResponse.ok();
    }
    @ApiOperation("删除系统公告")
    @PostMapping("/deleteSysNotice")
    public CommonResponse<?> deleteSysNotice(@RequestBody String id){
        Assert.notNull(id,"删除公告id不能为空！");
        service.deleteNoticeById(id);
        return CommonResponse.ok();
    }
    @ApiOperation("修改系统公告")
    @PostMapping("/updateSysNotice")
    public CommonResponse<?> updateSysNotice(@RequestBody SysNoticeForm form){
        Assert.notNull(form,"修改的系统公告不能为空！");
        service.updateNotice(form);
        return CommonResponse.ok();
    }
    @ApiOperation("查询系统公告")
    @PostMapping("/querySysNotice")
    public CommonResponse<?> querySysNotice(@RequestBody SysNoticeForm form){
        Assert.notNull(form,"查询表单不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<SysNoticeForm> list=service.queryNoticeList(form);
        return CommonResponse.ok(new PageInfo<>(list));
    }


    @ApiOperation("获取今日系统公告")
    @GetMapping("/querySysNoticeNow")
    public CommonResponse<?> querySysNotice(){
        //Assert.notNull(form,"查询表单不能为空！");
        Date date=new Date();
        Date day_begin = DateUtil.beginOfDay(date);
        Date day_end = DateUtil.endOfDay(date);
        SysNoticeForm form=new SysNoticeForm();
        form.setNoticeStarttime(day_begin);
        form.setNoticeEndtime(day_end);
        return CommonResponse.ok(service.queryNoticeList(form).get(0));
    }


}
