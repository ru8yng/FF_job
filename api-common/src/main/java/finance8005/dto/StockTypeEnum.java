package finance8005.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Title : ScheduleEnum
 * @Package :com.scjydz.enums
 * @Description :TODO（）
 * @Author: 杨亚茹
 * @Date: 创建时间 2022-05-16
 */
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum StockTypeEnum {

    AMERICAN_STOCK_MARKET("200", "us"),
    HONGKONG_STOCK_MARKET("100", "hk"),
    SHENZHEN_STOCK("51", "sz"),
    SHANGHAI_STOCK("1", "sh")
    ;

    private String code;

    private String descp;

    public static String getCodeForDescp(String descp) {
        for (StockTypeEnum stockTypeEnum : StockTypeEnum.values()) {
            if (descp.equals(stockTypeEnum.getDescp())) {
                return stockTypeEnum.code;
            }
        }
        return null;
    }

    public static String getDescpForCode(String code) {
        for (StockTypeEnum stockTypeEnum : StockTypeEnum.values()) {
            if (code.equals(stockTypeEnum.getCode())) {
                return stockTypeEnum.descp;
            }
        }
        return null;
    }

    public static StockTypeEnum getTypeByCode(String code) {
        if (code == null) {
            return null;
        }
        for (StockTypeEnum stockTypeEnum:  StockTypeEnum.values()){
            if(stockTypeEnum.getCode().equals(code)){
                return stockTypeEnum;
            }
        }
        return null;
    }


    public static String toJsonString() {
        SerializeConfig config = new SerializeConfig();
        config.configEnumAsJavaBean(StockTypeEnum.class);
        return JSON.toJSONString(StockTypeEnum.values(), config);
    }
}
