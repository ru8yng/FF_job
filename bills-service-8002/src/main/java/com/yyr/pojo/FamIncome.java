package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 家庭收入
 * @TableName fam_income
 */
@TableName(value ="fam_income")
@Data
public class FamIncome implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String famIncomeId;

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
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date updatedTime;

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
    private Date famIncomeTime;

    /**
     * 
     */
    private String famIncomeAmount;

    /**
     * 
     */
    private String famIncomeTypeId;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}