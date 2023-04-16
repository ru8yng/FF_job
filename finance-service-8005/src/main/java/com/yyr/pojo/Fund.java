package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 收藏基金
 * @TableName fund
 */
@TableName(value ="fund")
@Data
public class Fund implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String fund_id;

    private String fundCode;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(fill= FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField(fill= FieldFill.INSERT_UPDATE)
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

    private String famId;

    private String userId;

    @TableField(exist = false)
    private BigDecimal currentProfit;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}