package com.yyr.pojo;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 支出类型
 * @TableName expenses_type
 */
@TableName(value ="expenses_type")
@Data
public class ExpensesType implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String expenseTypeId;

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
    private String expenseTypeName;

    /**
     * 
     */
    private String expenseTypeDesc;

    private String famId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}