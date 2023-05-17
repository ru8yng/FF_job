package finance8005.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2023/3/28 9:35
 * @PackageName:com.yyr.dto
 * @ClassName: FundForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundForm {
    private String fundCode;

    private String userId;

    private String famId;

    private String fundId;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     *
     */
    private String fundName;

    /**
     *
     */
    private String fundType;

    /**
     * 当前净值
     */
    private BigDecimal currentNetValue;

    /**
     * 原费率
     */
    private BigDecimal sourceRate;

    /**
     * 现费率
     */
    private BigDecimal rate;

    /**
     * 货币基金每万分收益
     */
    private BigDecimal millionCopiesIncome;

    /**
     * 购买金额
     */
    private BigDecimal amount;

    @ApiParam("目前收益")
    private BigDecimal currentProfit;

    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;
}
