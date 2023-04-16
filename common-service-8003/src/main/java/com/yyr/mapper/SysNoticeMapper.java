package com.yyr.mapper;

import com.yyr.pojo.SysNotice;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author sheep
* @description 针对表【sys_notice(系统公告)】的数据库操作Mapper
* @createDate 2023-03-04 13:47:45
* @Entity com.yyr.pojo.SysNotice
*/
@Mapper
public interface SysNoticeMapper extends BaseMapper<SysNotice> {


}
