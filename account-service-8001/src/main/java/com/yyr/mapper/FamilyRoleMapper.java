package com.yyr.mapper;

import com.yyr.pojo.FamilyRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
* @description 针对表【family_role(用户家庭角色)】的数据库操作Mapper
* @createDate 2022-11-29 12:15:54
* @Entity com.yyr.pojo.FamilyRole
*/
@Mapper
public interface FamilyRoleMapper extends BaseMapper<FamilyRole> {

}




