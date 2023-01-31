package com.yyr.service;

import com.yyr.dto.FamRoleQueryForm;
import com.yyr.pojo.FamilyRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【family_role(用户家庭角色)】的数据库操作Service
* @createDate 2022-11-29 12:15:54
*/
public interface FamilyRoleService extends IService<FamilyRole> {
    void addFamRole(FamilyRole familyRole);
    void deleteFamRole(String id);
    void updateFamRole(FamilyRole familyRole);
    List<FamilyRole> queryFamRoleList(FamRoleQueryForm familyRole);
}
