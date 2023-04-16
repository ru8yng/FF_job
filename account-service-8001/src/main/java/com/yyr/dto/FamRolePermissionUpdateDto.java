package com.yyr.dto;

import com.yyr.pojo.FamPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/8 16:54
 * @PackageName:com.yyr.dto
 * @ClassName: FamRolePermissionUpdateDto
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamRolePermissionUpdateDto {
    private String roleId;
    private String famRoleDesc;

    private String famRoleName;

    private List<FamPermission> permissionList;
}
