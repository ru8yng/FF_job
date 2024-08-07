package com.yyr.service;


import com.yyr.pojo.Fund;
import com.baomidou.mybatisplus.extension.service.IService;
import finance8005.dto.*;

import java.util.Date;
import java.util.List;

/**
* @author sheep
* @description 针对表【fund(收藏基金)】的数据库操作Service
* @createDate 2023-03-28 09:33:58
*/
public interface FundService extends IService<Fund> {

    void addFund(FundForm form);

    void deleteFund(String fundId);

    void updateFund(FundForm form);

    List<FundForm> queryFund(FundForm form);

    CurrentFundNetValue queryCurrentFundNetValueByCode(String fundCode);

    HistoricalFundNetValue queryHistoricalFundNetValueByCode(String fundCode);

    List<FundcodeSearch> queryFundcodeSearch();

    void collectFund(FundCollectForm form);


    List<List<String>> getKLine(Date date);


}
