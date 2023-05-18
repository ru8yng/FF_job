package com.yyr.service;

import finance8005.dto.FundAndStockForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import utils.CommonResponse;

/**
 * @Author 杨亚茹
 * @Date 2023/4/29 14:36
 * @PackageName:com.yyr.service
 * @ClassName: finance_service_8005
 * @Description: TODO
 * @Version 1.0
 */

@FeignClient(value = "ffjob-finance-service8005")
public interface finance_service_8005 {

    @PostMapping("/FundAndStock/queryProfits")
    public CommonResponse<?> queryProfits(@RequestBody FundAndStockForm form);
}
