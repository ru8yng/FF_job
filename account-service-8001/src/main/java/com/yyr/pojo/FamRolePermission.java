package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 家庭角色权限
 * @TableName fam_role_permission
 */
@TableName(value ="fam_role_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamRolePermission implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String famRolePermissionId;

    /**
     * 
     */
    private String famRoleId;

    /**
     * 
     */
    private String famPermissionId;

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
        FamRolePermission other = (FamRolePermission) that;
        return (this.getFamRolePermissionId() == null ? other.getFamRolePermissionId() == null : this.getFamRolePermissionId().equals(other.getFamRolePermissionId()))
            && (this.getFamRoleId() == null ? other.getFamRoleId() == null : this.getFamRoleId().equals(other.getFamRoleId()))
            && (this.getFamPermissionId() == null ? other.getFamPermissionId() == null : this.getFamPermissionId().equals(other.getFamPermissionId()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFamRolePermissionId() == null) ? 0 : getFamRolePermissionId().hashCode());
        result = prime * result + ((getFamRoleId() == null) ? 0 : getFamRoleId().hashCode());
        result = prime * result + ((getFamPermissionId() == null) ? 0 : getFamPermissionId().hashCode());
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
        sb.append(", famRolePermissionId=").append(famRolePermissionId);
        sb.append(", famRoleId=").append(famRoleId);
        sb.append(", famPermissionId=").append(famPermissionId);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}