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




}
