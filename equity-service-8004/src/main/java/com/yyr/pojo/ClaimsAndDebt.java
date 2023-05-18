package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 借贷
 * @TableName claims_and_debt
 */
@TableName(value ="claims_and_debt")
@Data
public class ClaimsAndDebt implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String cadId;

    private String famId;
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
     * 0借入1借出
     */
    private String cadType;

    /**
     * 债权人
     */
    private String creditor;

    /**
     * 债务人
     */
    private String obligor;

    /**
     * 
     */
    private String creditorTel;

    /**
     * 
     */
    private String obligorTel;

    /**
     * 
     */
    private String cadAmount;

    /**
     * 借入/借出时间
     */
    private Date cadTime;

    /**
     * 偿还时间
     */
    private Date cadRepaymentTime;

    /**
     * 预计偿还时间
     */
    private Date cadPlanRepaymentTime;

    /**
     * 
     */
    private String remark;

    /**
     * 0已完成1仍存在
     */
    private String cadStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}