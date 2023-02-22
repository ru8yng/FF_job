package com.yyr.service;

import com.yyr.dto.OperaLogForm;
import com.yyr.pojo.SysOperaLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【sys_opera_log(操作日志)】的数据库操作Service
* @createDate 2023-02-19 17:10:03
*/
public interface SysOperaLogService extends IService<SysOperaLog> {
    List<SysOperaLog> querySysOperaLog(OperaLogForm form);
}
