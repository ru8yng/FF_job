package com.yyr.service;


import com.yyr.pojo.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
import finance8005.dto.StockForm;
import finance8005.dto.StockVS;
import finance8005.dto.StockVSForm;

import java.util.List;

/**
* @author sheep
* @description 针对表【stock(收藏股票)】的数据库操作Service
* @createDate 2023-04-11 14:56:07
*/
public interface StockService extends IService<Stock> {

    void addStock(StockForm form);

    void deleteStock(String stockId);

    void updateStock(StockForm form);

    List<StockForm> queryStock(StockForm form);

    StockVS queryStockCurrent(StockVSForm form);

    void collectStock(StockVSForm form);

}
