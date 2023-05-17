package com.yyr.service;

/**
 * @Author 杨亚茹
 * @Date 2023/4/25 14:43
 * @PackageName:com.yyr.service
 * @ClassName: equity_Service_8004
 * @Description: TODO
 * @Version 1.0
 */

import equity8004.dto.AcdaForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import utils.CommonResponse;

@FeignClient(value ="ffjob-equity-service8004")
public interface equity_service_8004 {
    @PostMapping("/acad/queryAcad")
    public CommonResponse<?> queryAcad(@RequestBody AcdaForm form);

}
