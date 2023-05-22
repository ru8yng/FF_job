package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统公告
 * @TableName sys_notice
 */
@TableName(value ="sys_notice")
@Data
public class SysNotice implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String sysNoticeId;

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
    private String noticeTitle;

    /**
     * 
     */
    private String noticeContent;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeStarttime;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeEndtime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}