package com.yyr.service.bp;

import com.alibaba.fastjson.JSONObject;
import com.yyr.config.ConfigProperties;
import com.yyr.dto.CurrentFundNetValue;
import com.yyr.dto.FundcodeSearch;
import com.yyr.dto.HistoricalFundNetValue;
import com.yyr.utils.HttpClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/3/27 17:33
 * @PackageName:com.yyr.service.bp
 * @ClassName: FundBp
 * @Description: TODO
 * @Version 1.0
 */

@Component
public class FundBp {
    @Autowired
    private ConfigProperties configProperties;

    public static  final String CURRENT_FUND_TIME_STAMP=".js?rt=1463558676006";

    public static  final String HISTORICAL_FUND_TIME_STAMP=".js?v=20160518155842";

    public CurrentFundNetValue getCurrentFundNetValue(String fundCode){
        String url=configProperties.getCURRENT_FUND_NET_VALUE_URL()+fundCode+CURRENT_FUND_TIME_STAMP;
        String response= HttpClient.doGet(url);
        //System.out.println("回复"+response);
        String result=response.replace("jsonpgz(","").replace(");","");
        CurrentFundNetValue value= JSONObject.parseObject(result, CurrentFundNetValue.class);
        return value;
    }

    public List<FundcodeSearch> getAllFund(){
        String url= configProperties.getFUNDCODE_SEARCH_URL();
        String response= HttpClient.doGet(url);
        String result=response.replace("var r = ","").replace("]];","]]");
        List<FundcodeSearch> list=JSONObject.parseObject(result, List.class);
        return list;
    }

    public HistoricalFundNetValue getHistoricalFundNetValue(String fundCode){
        String url= configProperties.getHISTORICAL_FUND_NET_VALUE_URL()+fundCode+HISTORICAL_FUND_TIME_STAMP;
        String response= HttpClient.doGet(url);
        HistoricalFundNetValue historicalFundNetValue=new HistoricalFundNetValue();
        CurrentFundNetValue currentFundNetValue=getCurrentFundNetValue(fundCode);

        historicalFundNetValue.setCode(fundCode);
        historicalFundNetValue.setName(currentFundNetValue.getName());

        String fund_sourceRate= StringUtils.substringBetween(response, "fund_sourceRate=\"","\";/*现费率*/");
        String fund_Rate= StringUtils.substringBetween(response, "fund_Rate=\"","\";/*最小申购金额*/");
        String fund_minsg= StringUtils.substringBetween(response, "fund_minsg=\"","\";/*基金持仓股票代码*/");
        String syl_1n= StringUtils.substringBetween(response, "syl_1n=\"","\";/*近6月收益率*/");
        String syl_6y= StringUtils.substringBetween(response, "syl_6y=\"","\";/*近三月收益率*/");
        String syl_3y= StringUtils.substringBetween(response, "syl_3y=\"","\";/*近一月收益率*/");
        String syl_1y= StringUtils.substringBetween(response, "syl_1y=\"","\";/*股票仓位测算图*/");

        historicalFundNetValue.setFund_sourceRate(BigDecimal.valueOf(Double.valueOf(fund_sourceRate)));
        historicalFundNetValue.setFund_Rate(BigDecimal.valueOf(Double.valueOf(fund_Rate)));
        historicalFundNetValue.setFund_minsg(BigDecimal.valueOf(Integer.parseInt(fund_minsg)));
        historicalFundNetValue.setSyl_1n(BigDecimal.valueOf(Double.valueOf(syl_1n)));
        historicalFundNetValue.setSyl_6y(BigDecimal.valueOf(Double.valueOf(syl_6y)));
        historicalFundNetValue.setSyl_3y(BigDecimal.valueOf(Double.valueOf(syl_3y)));
        historicalFundNetValue.setSyl_1y(BigDecimal.valueOf(Double.valueOf(syl_1y)));

        return historicalFundNetValue;
    }


}
