package com.yyr.mapper;

import com.yyr.pojo.FamRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_role_permission(家庭角色权限)】的数据库操作Mapper
* @createDate 2022-11-29 12:15:27
* @Entity com.yyr.pojo.FamRolePermission
*/
@Mapper
public interface FamRolePermissionMapper extends BaseMapper<FamRolePermission> {

    List<String> selectPermissionIdByRoleId(String roleId);

}




