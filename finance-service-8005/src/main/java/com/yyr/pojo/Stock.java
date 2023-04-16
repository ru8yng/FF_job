package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 收藏股票
 * @TableName stock
 */
@TableName(value ="stock")
@Data
public class Stock implements Serializable {
    /**
     * 行id，主键
     */
    @TableId(type=IdType.ASSIGN_ID)
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
    @TableField(fill= FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新人
     */
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 家庭id
     */
    private String famId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}