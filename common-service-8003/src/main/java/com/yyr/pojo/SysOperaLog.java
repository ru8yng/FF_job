package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    @TableId(type=IdType.ASSIGN_ID)
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