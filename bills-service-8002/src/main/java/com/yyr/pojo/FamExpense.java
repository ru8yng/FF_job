package com.yyr.pojo;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 家庭支出
 * @TableName fam_expense
 */
@TableName(value ="fam_expense")
@Data
public class FamExpense implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String famExpenseId;

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
     * 
     */
    private String userId;

    /**
     * 更新时间
     */
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    /**
     * 
     */
    private Date expenseTime;

    /**
     * 
     */
    private String famId;

    /**
     * 
     */
    private String famExpenseAmount;

    /**
     * 
     */
    private String famExpenseTypeId;

    /**
     * 
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}