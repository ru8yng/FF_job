package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.pojo.SysNotice;
import com.yyr.service.SysNoticeService;
import com.yyr.mapper.SysNoticeMapper;
import log8003.dto.SysNoticeForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author sheep
* @description 针对表【sys_notice(系统公告)】的数据库操作Service实现
* @createDate 2023-03-04 13:47:45
*/
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice>
implements SysNoticeService{

    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    @Override
    public void addNotice(SysNoticeForm form) {
        SysNotice notice=new SysNotice();
        BeanUtils.copyProperties(form,notice);
        sysNoticeMapper.insert(notice);

    }

    @Override
    public void deleteNoticeById(String id) {
        sysNoticeMapper.deleteById(id);
    }

    @Override
    public void updateNotice(SysNoticeForm form) {
        LambdaUpdateWrapper<SysNotice> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysNotice::getSysNoticeId,form.getSysNoticeId());
        if(form.getNoticeTitle()!= null && form.getNoticeTitle().length()!=0){
            updateWrapper.set(SysNotice::getNoticeTitle,form.getNoticeTitle());
        }
        if(form.getNoticeContent()!= null && form.getNoticeContent().length()!=0){
            updateWrapper.set(SysNotice::getNoticeContent,form.getNoticeContent());
        }
        if(form.getNoticeStarttime()!= null  && form.getNoticeEndtime()!=null){
            updateWrapper.set(SysNotice::getNoticeStarttime,form.getNoticeStarttime());
            updateWrapper.set(SysNotice::getNoticeEndtime,form.getNoticeEndtime());
        }

        if(form.getCreatedBy()!= null && form.getCreatedBy().length()!=0){
            updateWrapper.set(SysNotice::getCreatedBy,form.getCreatedBy());
        }
        this.update(updateWrapper);
    }

    @Override
    public List<SysNoticeForm> queryNoticeList(SysNoticeForm form) {
        LambdaQueryWrapper<SysNotice> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getNoticeTitle()!= null && form.getNoticeTitle().length()!=0){
            queryWrapper.like(SysNotice::getNoticeTitle,form.getNoticeTitle());
        }
        if(form.getNoticeContent()!=null && form.getNoticeContent().length()!=0){
            queryWrapper.like(SysNotice::getNoticeContent,form.getNoticeContent());
        }
        if(form.getNoticeStarttime()!= null && form.getNoticeEndtime()!=null){
            queryWrapper.le(SysNotice::getNoticeStarttime,form.getNoticeStarttime());
            queryWrapper.ge(SysNotice::getNoticeEndtime,form.getNoticeStarttime());
        }
        if(form.getCreatedBy()!=null && form.getCreatedBy().length()!=0){
            queryWrapper.eq(SysNotice::getCreatedBy,form.getCreatedBy());
        }
        List<SysNotice> list = this.list(queryWrapper);
        List<SysNoticeForm> forms=new ArrayList<>();
        list.forEach(notice->{
            SysNoticeForm form1=new SysNoticeForm();
            BeanUtils.copyProperties(notice,form1);
            forms.add(form1);
        });
        return forms;
    }
}
