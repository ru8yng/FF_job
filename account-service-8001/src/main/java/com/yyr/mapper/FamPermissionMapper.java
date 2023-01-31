package com.yyr.mapper;

import com.yyr.pojo.FamPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
* @description 针对表【fam_permission(家庭权限)】的数据库操作Mapper
* @createDate 2022-11-29 12:15:20
* @Entity com.yyr.pojo.FamPermission
*/
@Mapper
public interface FamPermissionMapper extends BaseMapper<FamPermission> {

}




