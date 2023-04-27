package com.yyr.service;

/**
 * @Author 杨亚茹
 * @Date 2023/4/25 14:43
 * @PackageName:com.yyr.service
 * @ClassName: equity_Service_8004
 * @Description: TODO
 * @Version 1.0
 */

import com.yyr.dto.BillsForm;
import com.yyr.dto.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value ="ffjob-equity-service8004")
public interface equity_service_8004 {
    @PostMapping("/cada/queryBills")
    public CommonResponse<?> queryBills(@RequestBody BillsForm form);

}
