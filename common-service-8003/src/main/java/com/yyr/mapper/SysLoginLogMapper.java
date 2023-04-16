package com.yyr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyr.pojo.SysLoginLog;
import org.apache.ibatis.annotations.Mapper;


/**
* @author sheep
* @description 针对表【sys_login_log(登录日志)】的数据库操作Mapper
* @createDate 2023-02-19 17:09:35
* @Entity com.yyr.pojo.SysLoginLog
*/
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {
}
