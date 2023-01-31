package com.yyr.mapper;

import com.yyr.pojo.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
* @description 针对表【sys_role(用户系统角色)】的数据库操作Mapper
* @createDate 2022-11-29 12:16:21
* @Entity com.yyr.pojo.SysRole
*/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}




