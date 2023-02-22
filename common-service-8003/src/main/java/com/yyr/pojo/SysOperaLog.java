package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 操作日志
 * @TableName sys_opera_log
 */
@TableName(value ="sys_opera_log")
@Data
public class SysOperaLog implements Serializable {
    /**
     * 
     */
    @TableId
    private String operaLogId;

    /**
     * 
     */
    private String operaLogName;

    /**
     * log
     */
    private String operaLog;

    /**
     * 
     */
    private Date operaLogOperatime;

    /**
     * 
     */
    private String ipaddr;

    /**
     * sysRole
     */
    private String operaLogSysroleid;

    /**
     * 创建人(user_id)
     */
    private String operaLogOperaby;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}