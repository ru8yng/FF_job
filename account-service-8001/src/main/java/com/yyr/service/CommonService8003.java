package com.yyr.service;

import com.yyr.dto.CommonResponse;
import com.yyr.dto.LoginLogForm;
import com.yyr.dto.OperaLogForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
