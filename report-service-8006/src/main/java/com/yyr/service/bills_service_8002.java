package com.yyr.service;

import com.yyr.dto.CommonResponse;
import com.yyr.dto.IAEForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
