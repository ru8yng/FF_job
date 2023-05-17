package com.yyr.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 家庭预算
 * @TableName fam_budget
 */
@TableName(value ="fam_budget")
@Data
public class FamBudget implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String famBudgetId;

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
    @TableField(fill= FieldFill.INSERT)
    private Date updatedTime;

    /**
     * 
     */
    private String famBudgetAmount;

    /**
     * 
     */
    private String famId;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private String userBudgetAmount;


    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}