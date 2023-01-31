package com.yyr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/1/27 15:48
 * @PackageName:com.yyr.dto
 * @ClassName: FamRolePermissionAddForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamRolePermissionForm {
    String fam_role_id;
    List<String> fam_permission_id;
}
