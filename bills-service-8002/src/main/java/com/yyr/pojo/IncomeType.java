package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收入类型
 * @TableName income_type
 */
@TableName(value ="income_type")
@Data
public class IncomeType implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String incomeTypeId;

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
    private String incomeTypeName;

    /**
     * 
     */
    private String incomeTypeDesc;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}