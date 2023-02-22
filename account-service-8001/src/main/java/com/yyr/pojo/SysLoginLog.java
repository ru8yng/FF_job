package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 登录日志
 * @TableName sys_login_log
 */
@TableName(value ="sys_login_log")
@Data
public class SysLoginLog implements Serializable {
    /**
     * 
     */
    @TableId
    private String loginLogId;

    /**
     * 
     */
    private String userName;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private String ipaddr;

    /**
     * 
     */
    private Date loginTime;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}