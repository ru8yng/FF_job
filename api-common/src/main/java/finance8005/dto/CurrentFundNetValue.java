package finance8005.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author 杨亚茹
 * @Date 2023/3/27 16:58
 * @PackageName:com.yyr.dto
 * @ClassName: Current_fund_net_value
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentFundNetValue {
    private String fundcode;
    private String name;
    @ApiParam("基金开始日期")
    private String jzrq;

    @ApiParam("单位净值")
    private BigDecimal dwjz;

    @ApiParam("估算净值")
    private Double gsz;

    @ApiParam("估计涨幅")
    private Double gszzl;

    @ApiParam("估计时间")
    private String gztime;

}
