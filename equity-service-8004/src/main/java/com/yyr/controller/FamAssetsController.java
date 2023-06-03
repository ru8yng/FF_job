package com.yyr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.config.logCustom;
import com.yyr.pojo.FamAssets;
import com.yyr.service.FamAssetsService;
import equity8004.dto.FamAssetsForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import utils.CommonResponse;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/7 22:12
 * @PackageName:com.yyr.controller
 * @ClassName: FamAssetsController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "固定资产")
@RequestMapping("/assets")
@RestController
@Slf4j(topic = "assets")
public class FamAssetsController {

    @Autowired
    private FamAssetsService famAssetsService;

    @ApiOperation("新增固定资产")
    @logCustom(description = "新增固定资产")
    @PostMapping("/addFamAssets")
    public CommonResponse<?> addFamAssets(@RequestBody FamAssetsForm form){
        Assert.notNull(form,"新增固定资产不能为空！");
        famAssetsService.addAssets(form);
        return CommonResponse.ok("新增固定资产成功！");
    }

    @ApiOperation("删除固定资产")
    @logCustom(description = "删除固定资产")
    @GetMapping("/deleteFamAssets/{assetsId}")
    public CommonResponse<?> deleteFamAssets(@PathVariable String assetsId){
        Assert.notNull(assetsId,"固定资产id不能为空！");
        famAssetsService.deleteAssets(assetsId);
        return CommonResponse.ok("删除固定资产成功！");
    }

    @ApiOperation("更新固定资产")
    @logCustom(description = "更新固定资产")
    @PostMapping("/updateFamAssets")
    public CommonResponse<?> updateFamAssets(@RequestBody FamAssetsForm form){
        Assert.notNull(form,"更新的固定资产不能为空！");
        famAssetsService.updateAssets(form);
        return CommonResponse.ok("更新固定资产成功！");
    }


    @ApiOperation("查询固定资产")
    @PostMapping("/queryFamAssets")
    public CommonResponse<?> queryFamAssetse(@RequestBody FamAssetsForm form){
        Assert.notNull(form,"FamAssetsForm不能为空！");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<FamAssets> list=famAssetsService.queryAssets(form);
        return CommonResponse.ok(new PageInfo<>(list));

    }

}
