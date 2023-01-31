package com.yyr.mapper;

import com.yyr.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-11-29 12:14:55
* @Entity com.yyr.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




