package com.yyr.service;

import account8001.dto.FamQueryForm;
import account8001.dto.FamRolePermissionUpdateDto;
import account8001.dto.FamRoleQueryForm;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyr.pojo.FamPermission;
import com.yyr.pojo.FamilyRole;

import java.util.List;

/**
* @author sheep
* @description 针对表【family_role(用户家庭角色)】的数据库操作Service
* @createDate 2022-11-29 12:15:54
*/
public interface FamilyRoleService extends IService<FamilyRole> {
    void addFamRole(FamRoleQueryForm familyRole);
    void deleteFamRole(String id);
    void updateFamRole(FamRolePermissionUpdateDto dto);
    List<FamRoleQueryForm> queryFamRoleList(FamRoleQueryForm familyRole);

    List<FamPermission> queryFamPermissionByFamRoleId(String roleId, boolean isFilter);

    public List<FamPermission> buildTree(List<FamPermission> permissionSet);
}
