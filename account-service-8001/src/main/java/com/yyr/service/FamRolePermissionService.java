package com.yyr.service;

import com.yyr.dto.FamRolePermissionForm;
import com.yyr.pojo.FamRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_role_permission(家庭角色权限)】的数据库操作Service
* @createDate 2022-11-29 12:15:27
*/
public interface FamRolePermissionService extends IService<FamRolePermission> {
    void addFamRolePermissionByFamRoleId(FamRolePermissionForm form);

    void deleteFamRolePermissionByFamRolePermissionForm(FamRolePermissionForm form);

    List<FamRolePermission> queryFamRolePermissionByFamRoleId(FamRolePermissionForm form);

}
