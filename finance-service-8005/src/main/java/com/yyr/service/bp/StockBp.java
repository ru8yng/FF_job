package com.yyr.service.bp;

import com.ctc.wstx.util.StringUtil;
import com.yyr.config.ConfigProperties;
import com.yyr.service.StockService;
import finance8005.dto.StockTypeEnum;
import finance8005.dto.StockVS;
import finance8005.dto.StockVSForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.HttpClient;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @Author 杨亚茹
 * @Date 2023/4/16 17:04
 * @PackageName:com.yyr.service.bp
 * @ClassName: StockBp
 * @Description: TODO
 * @Version 1.0
 */

@Component
public class StockBp {

    @Autowired
    private ConfigProperties configProperties;

    public StockVS getStockVS(StockVSForm form){
        StockVS vs=new StockVS();
        BigDecimal zero= BigDecimal.valueOf(0);
        String url= configProperties.getSTOCK_V_S()+form.getType()+form.getStockCode();
        String response= HttpClient.doGetStock(url);
        String[] result= StringUtils.substringBetween(response,"=\"","\";").split("~");
        vs.setStock_type(StockTypeEnum.getDescpForCode(result[0]));
        vs.setName(result[1]);
        vs.setCode(result[2]);
        vs.setCurrent(BigDecimal.valueOf(Double.parseDouble(result[3])));
        vs.setUd(BigDecimal.valueOf(Double.parseDouble(result[4])));
        vs.setUdPercent(BigDecimal.valueOf(Double.parseDouble(result[5])/100));
        vs.setTurnover(result[6].isEmpty()?zero:BigDecimal.valueOf(Double.parseDouble(result[6])));
        vs.setTurnover_amount(result[7].isEmpty()?zero:BigDecimal.valueOf(Double.parseDouble(result[7])));
        vs.setTurnoverRate(result[8].isEmpty()?zero:BigDecimal.valueOf(Double.parseDouble(result[8])));
        vs.setGp_a(result[9]);

        return vs;

    }


}
