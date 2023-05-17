package com.yyr.service.impl;

import com.yyr.service.FundAndStockService;
import com.yyr.service.FundService;
import com.yyr.service.StockService;
import finance8005.dto.FundAndStockForm;
import finance8005.dto.FundForm;
import finance8005.dto.StockForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/29 14:26
 * @PackageName:com.yyr.service
 * @ClassName: FundAndStockServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class FundAndStockServiceImpl implements FundAndStockService {

    @Autowired
    private FundService fundService;


    @Autowired
    private StockService stockService;

    @Override
    public FundAndStockForm getFundAndStock(FundAndStockForm form) {
        FundForm fundForm=new FundForm();
        StockForm stockForm=new StockForm();
        fundForm.setUserId(form.getUserId());
        stockForm.setUserId(form.getUserId());
        List<FundForm> fundFormList=fundService.queryFund(fundForm);
        List<StockForm> stockFormList=stockService.queryStock(stockForm);
        form.setFundFormList(fundFormList);
        form.setStockFormList(stockFormList);
        return form;
    }
}
