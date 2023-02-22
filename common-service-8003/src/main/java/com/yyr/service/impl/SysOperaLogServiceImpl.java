package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.OperaLogForm;
import com.yyr.pojo.SysOperaLog;
import com.yyr.service.SysOperaLogService;
import com.yyr.mapper.SysOperaLogMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author sheep
* @description 针对表【sys_opera_log(操作日志)】的数据库操作Service实现
* @createDate 2023-02-19 17:10:03
*/
@Service
public class SysOperaLogServiceImpl extends ServiceImpl<SysOperaLogMapper, SysOperaLog>
implements SysOperaLogService{

    @Override
    public List<SysOperaLog> querySysOperaLog(OperaLogForm form) {
        LambdaQueryWrapper<SysOperaLog> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getOperaLogId()!=null && form.getOperaLogId().length()!=0){
            queryWrapper.eq(SysOperaLog::getOperaLogId,form.getOperaLogId());
        }
        if(form.getOperaLogName()!=null && form.getOperaLogName().length()!=0){
            queryWrapper.eq(SysOperaLog::getOperaLogName,form.getOperaLogName());
        }
        if(form.getOperaLog()!=null && form.getOperaLog().length()!=0){
            queryWrapper.eq(SysOperaLog::getOperaLog,form.getOperaLog());
        }
        if(form.getIpaddr()!=null && form.getIpaddr().length()!=0){
            queryWrapper.eq(SysOperaLog::getIpaddr,form.getIpaddr());
        }
        if(form.getOperaLogSysroleId()!=null && form.getOperaLogSysroleId().length()!=0){
            queryWrapper.eq(SysOperaLog::getOperaLogSysroleid,form.getOperaLogSysroleId());
        }
        if(form.getOperaLogOperaby()!=null && form.getOperaLogOperaby().length()!=0){
            queryWrapper.eq(SysOperaLog::getOperaLogOperaby,form.getOperaLogOperaby());
        }
        if(form.getStartTime()!=null && form.getStartTime().length()!=0){
            queryWrapper.ge(SysOperaLog::getOperaLogOperatime,form.getStartTime());
        }
        if(form.getEndTime()!=null && form.getEndTime().length()!=0){
            queryWrapper.le(SysOperaLog::getOperaLogOperatime,form.getEndTime());
        }
        queryWrapper.orderByAsc(SysOperaLog::getOperaLogOperatime);

        return this.list(queryWrapper);
    }
}
