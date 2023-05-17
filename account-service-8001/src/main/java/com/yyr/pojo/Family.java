package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 家庭表
 * @TableName family
 */
@TableName(value ="family")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Family implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String familyId;

    /**
     * 
     */
    private String familyName;

    /**
     * 
     */
    private String familyDesc;

    /**
     * 创建人(user_id)
     */
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(fill= FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新人(user_id)
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}