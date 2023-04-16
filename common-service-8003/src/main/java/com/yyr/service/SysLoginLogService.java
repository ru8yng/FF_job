package com.yyr.service;


import com.yyr.dto.LoginLogForm;
import com.yyr.pojo.SysLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【sys_login_log(登录日志)】的数据库操作Service
* @createDate 2023-02-19 17:09:35
*/
public interface SysLoginLogService extends IService<SysLoginLog> {
    /**
     * @description:根据查询条件获取日志信息
     * @Param: [from]
     * @return:
     * @throws:
     * @author:杨亚茹
     */
    List<SysLoginLog> queryLoginLogList(LoginLogForm form);

    /**
    * @description:新增登录日志
    * @Param: [form]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    void addLoginLog(LoginLogForm form);

}
