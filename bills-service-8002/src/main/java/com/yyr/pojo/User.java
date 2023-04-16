package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private String userId;

    /**
     * 
     */
    private String userName;

    /**
     * 
     */
    private String userPwd;

    /**
     * 
     */
    private String sex;

    /**
     * 
     */

    private Date birthday;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String qqnum;

    /**
     * 用户系统角色id
     */
    private String sysRoleId;

    /**
     * 家庭id
     */
    private String familyId;

    /**
     * 用户家庭角色id
     */
    private String famRoleId;

    /**
     * 用户账号状态
     */
    private String status;

    /**
     * 创建时间
     */

    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */

    private Date updatedTime;


}