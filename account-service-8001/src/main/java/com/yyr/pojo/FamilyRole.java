package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户家庭角色
 * @TableName family_role
 */
@TableName(value ="family_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRole implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String famRoleId;

    /**
     * 
     */
    private String famRoleName;

    /**
     * 
     */
    private String famRoleDesc;

    /**
     * 
     */
    private String famId;

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


    @TableField(exist = false)
    private List<FamPermission> permissionList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FamilyRole other = (FamilyRole) that;
        return (this.getFamRoleId() == null ? other.getFamRoleId() == null : this.getFamRoleId().equals(other.getFamRoleId()))
            && (this.getFamRoleName() == null ? other.getFamRoleName() == null : this.getFamRoleName().equals(other.getFamRoleName()))
            && (this.getFamRoleDesc() == null ? other.getFamRoleDesc() == null : this.getFamRoleDesc().equals(other.getFamRoleDesc()))
            && (this.getFamId() == null ? other.getFamId() == null : this.getFamId().equals(other.getFamId()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFamRoleId() == null) ? 0 : getFamRoleId().hashCode());
        result = prime * result + ((getFamRoleName() == null) ? 0 : getFamRoleName().hashCode());
        result = prime * result + ((getFamRoleDesc() == null) ? 0 : getFamRoleDesc().hashCode());
        result = prime * result + ((getFamId() == null) ? 0 : getFamId().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedBy() == null) ? 0 : getUpdatedBy().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", famRoleId=").append(famRoleId);
        sb.append(", famRoleName=").append(famRoleName);
        sb.append(", famRoleDesc=").append(famRoleDesc);
        sb.append(", famId=").append(famId);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}