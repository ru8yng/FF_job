package com.yyr.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 家庭权限
 * @TableName fam_permission
 */
@TableName(value ="fam_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamPermission implements Serializable {
    /**
     * 
     */
    @TableId(type=IdType.ASSIGN_ID)
    private String famPermissionId;

    /**
     * 权限名
     */
    private String famPermissionName;

    /**
     * 权限路径(前端)
     */
    private String famPermissionPath;

    /**
     * 
     */
    private String famPermissionParentid;

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

    @TableField(exist = false)
    private List<FamPermission> child = new ArrayList<>();

    @ApiModelProperty("检查选中没选中 ， 0未选中，1选中")
    @TableField(exist = false)
    private String check;


    private String icon;

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
        FamPermission other = (FamPermission) that;
        return (this.getFamPermissionId() == null ? other.getFamPermissionId() == null : this.getFamPermissionId().equals(other.getFamPermissionId()))
            && (this.getFamPermissionName() == null ? other.getFamPermissionName() == null : this.getFamPermissionName().equals(other.getFamPermissionName()))
            && (this.getFamPermissionPath() == null ? other.getFamPermissionPath() == null : this.getFamPermissionPath().equals(other.getFamPermissionPath()))
            && (this.getFamPermissionParentid() == null ? other.getFamPermissionParentid() == null : this.getFamPermissionParentid().equals(other.getFamPermissionParentid()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFamPermissionId() == null) ? 0 : getFamPermissionId().hashCode());
        result = prime * result + ((getFamPermissionName() == null) ? 0 : getFamPermissionName().hashCode());
        result = prime * result + ((getFamPermissionPath() == null) ? 0 : getFamPermissionPath().hashCode());
        result = prime * result + ((getFamPermissionParentid() == null) ? 0 : getFamPermissionParentid().hashCode());
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
        sb.append(", famPermissionId=").append(famPermissionId);
        sb.append(", famPermissionName=").append(famPermissionName);
        sb.append(", famPermissionPath=").append(famPermissionPath);
        sb.append(", famPermissionParentid=").append(famPermissionParentid);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}