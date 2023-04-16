package com.yyr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.pojo.SysNotice;
import com.yyr.service.SysNoticeService;
import com.yyr.mapper.SysNoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author sheep
* @description 针对表【sys_notice(系统公告)】的数据库操作Service实现
* @createDate 2023-03-04 13:47:45
*/
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice>
implements SysNoticeService{

}
