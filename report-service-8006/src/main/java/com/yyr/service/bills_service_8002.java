package com.yyr.service;


import bills8002.dto.IAEForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import utils.CommonResponse;

/**
 * @Author 杨亚茹
 * @Date 2023/4/25 15:36
 * @PackageName:com.yyr.service
 * @ClassName: bills_service_8002
 * @Description: TODO
 * @Version 1.0
 */

@FeignClient(value ="ffjob-bills-service8002")
public interface bills_service_8002 {
    @PostMapping("/IAE/queryIaeCurrent")
    public CommonResponse<?> queryIaeCurrent(@RequestBody IAEForm iae);
}
