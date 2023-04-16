package com.yyr.service;

import com.yyr.dto.CurrentFundNetValue;
import com.yyr.dto.FundForm;
import com.yyr.dto.FundcodeSearch;
import com.yyr.dto.HistoricalFundNetValue;
import com.yyr.pojo.Fund;
import com.baomidou.mybatisplus.extension.service.IService;

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

    List<Fund> queryFund(FundForm form);

    CurrentFundNetValue queryCurrentFundNetValueByCode(String fundCode);

    HistoricalFundNetValue queryHistoricalFundNetValueByCode(String fundCode);

    List<FundcodeSearch> queryFundcodeSearch();

    void collectFund(String fundCode);

}
