package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 固定资产
 * @TableName fam_assets
 */
@TableName(value ="fam_assets")
@Data
public class FamAssets implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String famAssetsId;

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
    private String assetsName;

    /**
     * 更新时间
     */
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    /**
     * 
     */
    private String assetsLocation;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assetsBuytime;

    /**
     * 
     */
    private String famId;

    /**
     * 资产分期(0为不分期1为分期)
     */
    private String assetsInstalment;

    /**
     * 剩余期限/月
     */
    private String instalmentSurplus;

    /**
     * 每期还款金额
     */
    private String instalmentPrice;

    /**
     * 
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}