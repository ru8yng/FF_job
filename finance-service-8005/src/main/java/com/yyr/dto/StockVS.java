package com.yyr.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author 杨亚茹
 * @Date 2023/4/16 17:08
 * @PackageName:com.yyr.dto
 * @ClassName: StockVS
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockVS {

    private String Stock_type;

    private String name;

    private String code;

    private BigDecimal current;

    @ApiParam("涨跌")
    private BigDecimal ud;

    @ApiParam("涨跌百分比")
    private BigDecimal udPercent;


    @ApiParam("成交量")
    private BigDecimal turnover;

    @ApiParam("成交额")
    private BigDecimal turnover_amount;

    @ApiParam("换手率")
    private BigDecimal turnoverRate;


    @ApiParam("总市值")
    private String gp_a;





}
