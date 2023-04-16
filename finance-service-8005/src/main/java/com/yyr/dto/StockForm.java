package com.yyr.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2023/4/11 14:56
 * @PackageName:com.yyr.dto
 * @ClassName: StockForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockForm {
    /**
     * 行id，主键
     */
    @TableId
    private String stockId;

    /**
     * 股市代码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 股票类型
     */
    private String stockType;

    /**
     * 每股价格
     */
    private BigDecimal stockPrice;

    /**
     * 购买数量
     */
    private BigDecimal stockNum;

    /**
     * 购买时间
     */
    private Date stockTime;

    /**
     * 购买人id
     */
    private String userId;

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
     * 家庭id
     */
    private String famId;

    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;
}
