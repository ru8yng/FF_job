package finance8005.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author 杨亚茹
 * @Date 2023/3/27 17:13
 * @PackageName:com.yyr.dto
 * @ClassName: Historical_fund_net_value
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricalFundNetValue {

    @ApiParam("基金名")
    private String name;

    private String code;

    @ApiParam("原费率")
    private BigDecimal fund_sourceRate;

    @ApiParam("现费率")
    private BigDecimal fund_Rate;

    @ApiParam("最小申购金额")
    private BigDecimal fund_minsg;

    @ApiParam("近一年收益率")
    private BigDecimal syl_1n;

    @ApiParam("近6月收益率")
    private BigDecimal syl_6y;

    @ApiParam("近3月收益率")
    private BigDecimal syl_3y;

    @ApiParam("近1月收益率")
    private BigDecimal syl_1y;

}
