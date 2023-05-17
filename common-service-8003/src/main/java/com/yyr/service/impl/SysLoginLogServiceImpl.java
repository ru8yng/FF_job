package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.pojo.SysLoginLog;
import com.yyr.service.SysLoginLogService;
import com.yyr.mapper.SysLoginLogMapper;
import log8003.dto.LoginLogForm;
import org.springframework.stereotype.Service;
import java.util.List;

/**
* @author sheep
* @description 针对表【sys_login_log(登录日志)】的数据库操作Service实现
* @createDate 2023-02-19 17:09:35
*/
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog>
implements SysLoginLogService{


    @Override
    public List<SysLoginLog> queryLoginLogList(LoginLogForm form) {
        LambdaQueryWrapper<SysLoginLog> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getLogin_log_Id()!=null && form.getLogin_log_Id().length()!=0){
            queryWrapper.eq(SysLoginLog::getLoginLogId,form.getLogin_log_Id());
        }
        if(form.getUserId()!=null && form.getUserId().length()!=0){
            queryWrapper.eq(SysLoginLog::getUserId,form.getUserId());
        }
        if(form.getUsername()!=null && form.getUsername().length()!=0){
            queryWrapper.eq(SysLoginLog::getUserName,form.getUsername());
        }
        if(form.getIpAddr()!=null && form.getIpAddr().length()!=0){
            queryWrapper.eq(SysLoginLog::getIpaddr,form.getIpAddr());
        }
        if(form.getStartTime()!=null && form.getStartTime().length()!=0){
            queryWrapper.ge(SysLoginLog::getLoginTime,form.getStartTime());
        }
        if(form.getEndTime()!=null && form.getEndTime().length()!=0){
            queryWrapper.le(SysLoginLog::getLoginLogId,form.getEndTime());
        }
        queryWrapper.orderByAsc(SysLoginLog::getLoginTime);
        List<SysLoginLog> list=this.list(queryWrapper);
        return list;
    }

    @Override
    public void addLoginLog(LoginLogForm form) {
        SysLoginLog loginLog=new SysLoginLog();
        if(form.getUserId()!=null && form.getUserId().length()!=0){
            loginLog.setUserId(form.getUserId());
        }
        if(form.getUsername()!=null && form.getUsername().length()!=0){
            loginLog.setUserName(form.getUsername());
        }
        if(form.getIpAddr()!=null && form.getIpAddr().length()!=0){
            loginLog.setIpaddr(form.getIpAddr());
        }

        if(form.getIpAddr()!=null && form.getIpAddr().length()!=0){
            loginLog.setLoginTime(form.getLoginTime());
        }

        this.baseMapper.insert(loginLog);

    }

}
