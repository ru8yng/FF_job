package com.yyr.service;


import log8003.dto.LoginLogForm;
import log8003.dto.OperaLogForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import utils.CommonResponse;

/**
 * @Author 杨亚茹
 * @Date 2023/2/26 10:31
 * @PackageName:com.yyr.service
 * @ClassName: CommonService8003
 * @Description: TODO
 * @Version 1.0
 */

@FeignClient(value = "ffjob-common-service8003")
public interface CommonService8003 {

    @PostMapping("/sysOperaLog/addSysOperaLog")
    public CommonResponse<?> addSysOperaLog(@RequestBody OperaLogForm form);

    @PostMapping("/sysLoginLog/addSysLoginLog")
    public CommonResponse<?> addSysLoginLog(@RequestBody LoginLogForm form);


    }
