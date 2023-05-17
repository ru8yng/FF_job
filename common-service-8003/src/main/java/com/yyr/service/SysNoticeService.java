package com.yyr.service;

import com.yyr.pojo.SysNotice;
import com.baomidou.mybatisplus.extension.service.IService;
import log8003.dto.SysNoticeForm;

import java.util.List;

/**
* @author sheep
* @description 针对表【sys_notice(系统公告)】的数据库操作Service
* @createDate 2023-03-04 13:47:45
*/
public interface SysNoticeService extends IService<SysNotice> {

    void addNotice(SysNoticeForm form);

    void deleteNoticeById(String id);

    void updateNotice(SysNoticeForm form);

    List<SysNoticeForm> queryNoticeList(SysNoticeForm form);

}
