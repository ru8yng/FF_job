package com.yyr.service;


import account8001.dto.UserQueryForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import utils.CommonResponse;

/**
 * @Author 杨亚茹
 * @Date 2023/3/28 16:42
 * @PackageName:com.yyr.service
 * @ClassName: AccountService8001
 * @Description: TODO
 * @Version 1.0
 */

@FeignClient(value = "ffjob-account-service8001")
public interface AccountService8001 {
    @GetMapping(value = "/auth/getUserInfo", produces = "application/json;charset=UTF-8")
    public CommonResponse<?> getUserInfo(@RequestHeader(value ="X-Token") String token);

    @PostMapping("/user/queryUserList")
    public CommonResponse<?> queryUserList(@RequestBody UserQueryForm form);

}
