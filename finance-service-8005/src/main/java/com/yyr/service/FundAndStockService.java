package com.yyr.service;

import finance8005.dto.FundAndStockForm;
import org.springframework.stereotype.Service;

/**
 * @Author 杨亚茹
 * @Date 2023/4/29 14:25
 * @PackageName:com.yyr.service
 * @ClassName: FundAndStockService
 * @Description: TODO
 * @Version 1.0
 */

public interface FundAndStockService {
    FundAndStockForm getFundAndStock(FundAndStockForm form);
}
