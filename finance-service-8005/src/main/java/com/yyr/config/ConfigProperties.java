package com.yyr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author 杨亚茹
 * @Date 2023/3/27 16:35
 * @PackageName:com.yyr.config
 * @ClassName: ConfigProperties
 * @Description: TODO
 * @Version 1.0
 */

@Data
@Component
public class ConfigProperties {
    @Value("http://fund.eastmoney.com/js/fundcode_search.js")
    private String FUNDCODE_SEARCH_URL;

    //http://fundgz.1234567.com.cn/js/001186.js?rt=1463558676006  获取001186当前净值
    @Value("http://fundgz.1234567.com.cn/js/")
    private String CURRENT_FUND_NET_VALUE_URL;

    //http://fund.eastmoney.com/pingzhongdata/001186.js?v=20160518155842
    @Value("http://fund.eastmoney.com/pingzhongdata/")
    private String HISTORICAL_FUND_NET_VALUE_URL;

    //http://qt.gtimg.cn/q=s_sh600519
    @Value("http://qt.gtimg.cn/q=s_")
    private String STOCK_V_S;

    //sh000001--上证指数；
    //param=代码,日k，开始日期，结束日期，获取多少个交易日，前复权
    //http://web.ifzq.gtimg.cn/appstock/app/fqkline/get?param=sh000001,day,2017-12-01,,640,qfq
    //返回值：["2019-09-30","2927.920","2905.190","2936.480","2905.190","116646811.000"]],
    //交易日，开盘价，收盘价，最高价，最低价,总手
    @Value("https://web.ifzq.gtimg.cn/appstock/app/fqkline/get?param=sh000001,day,")
    private String KLINE;




}
