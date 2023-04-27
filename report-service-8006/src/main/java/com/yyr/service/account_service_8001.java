package com.yyr.service;

import com.yyr.dto.CommonResponse;
import com.yyr.dto.UserQueryForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @Author 杨亚茹
 * @Date 2023/4/21 21:32
 * @PackageName:com.yyr.service
 * @ClassName: account_service_8001
 * @Description: TODO
 * @Version 1.0
 */

@FeignClient(value = "ffjob-account-service8001")
public interface account_service_8001 {
    @PostMapping("/user/queryUserList")
    public CommonResponse<?> queryUserList(@RequestBody UserQueryForm form);

    @GetMapping(value = "/user/getUserInfo", produces = "application/json;charset=UTF-8")
    public CommonResponse<?> getUserInfo(@RequestHeader(value ="X-Token") String token);

    }
