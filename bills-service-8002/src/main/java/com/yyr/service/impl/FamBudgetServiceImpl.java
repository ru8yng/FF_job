package com.yyr.service.impl;

import account8001.dto.UserQueryForm;
import bills8002.dto.FamBudgetForm;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.pojo.FamBudget;
import com.yyr.pojo.FamExpense;
import com.yyr.service.AccountService8001;
import com.yyr.service.FamBudgetService;
import com.yyr.mapper.FamBudgetMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
* @author sheep
* @description 针对表【fam_budget(家庭预算)】的数据库操作Service实现
* @createDate 2023-03-28 15:49:21
*/
@Service
public class FamBudgetServiceImpl extends ServiceImpl<FamBudgetMapper, FamBudget>
    implements FamBudgetService{

    @Autowired
    private AccountService8001 accountService8001;

    @Autowired
    private FamBudgetMapper famBudgetMapper;

    @Override
    public void addFamBudget(FamBudgetForm form) {
        Date begin = DateUtil.beginOfMonth(new Date());
        Date end = DateUtil.endOfMonth(new Date());
        FamBudgetForm budgetForm = new FamBudgetForm();
        budgetForm.setUserId(form.getUserId());
        budgetForm.setStartTime(begin);
        budgetForm.setEndTime(end);
        Assert.isTrue(queryFamBudget(budgetForm).size()==0,"该用户本月已设置预算！");
        FamBudget famBudget=new FamBudget();
        BeanUtils.copyProperties(form,famBudget);
        this.save(famBudget);

    }

    @Override
    public void deleteFamBudget(String budgetId) {
        FamBudget famBudget=famBudgetMapper.selectById(budgetId);
        Assert.notNull(famBudget,"该预算不存在");
        this.removeById(budgetId);
    }

    @Override
    public void updateFamBudget(FamBudgetForm form) {

        LambdaUpdateWrapper<FamBudget> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(FamBudget::getFamBudgetId,form.getFamBudgetId());

        if(form.getFamId()!=null && form.getFamId().length()!=0){
            updateWrapper.set(FamBudget::getFamId,form.getFamId());
        }
        if(form.getUserId()!=null && form.getUserId().length()!=0){
            updateWrapper.set(FamBudget::getUserId,form.getUserId());
        }
        if(form.getStartTime()!=null&& form.getEndTime()!=null){
            updateWrapper.set(FamBudget::getStartTime,form.getStartTime());
            updateWrapper.set(FamBudget::getEndTime,form.getEndTime());

        }

        if(form.getCreatedBy()!=null && form.getCreatedBy().length()!=0){
            updateWrapper.like(FamBudget::getCreatedBy,form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null && form.getUpdatedBy().length()!=0){
            updateWrapper.like(FamBudget::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getFamBudgetAmount()!=null ){
            updateWrapper.set(FamBudget::getFamBudgetAmount,form.getFamBudgetAmount());
        }
        if(form.getUserBudgetAmount()!=null ){
            updateWrapper.set(FamBudget::getUserBudgetAmount,form.getUserBudgetAmount());
        }

        this.update(updateWrapper);


    }

    @Override
    public List<FamBudget> queryFamBudget(FamBudgetForm form) {

        LambdaQueryWrapper<FamBudget> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(form.getFamBudgetId()!=null && form.getFamBudgetId().length()!=0){
            lambdaQueryWrapper.eq(FamBudget::getFamBudgetId,form.getFamBudgetId());
        }
        if(form.getFamId()!=null && form.getFamId().length()!=0){
            lambdaQueryWrapper.eq(FamBudget::getFamId,form.getFamId());
        }
        if(form.getUserId()!=null && form.getUserId().length()!=0){
            lambdaQueryWrapper.eq(FamBudget::getUserId,form.getUserId());
        }

        if(form.getCreatedBy()!=null && form.getCreatedBy().length()!=0){
            lambdaQueryWrapper.like(FamBudget::getCreatedBy,form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null && form.getUpdatedBy().length()!=0){
            lambdaQueryWrapper.like(FamBudget::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getStartTime()!=null && form.getEndTime()!=null ){
            lambdaQueryWrapper.between(FamBudget::getStartTime,form.getStartTime(),form.getEndTime());
            lambdaQueryWrapper.between(FamBudget::getEndTime,form.getStartTime(),form.getEndTime());

        }

        List<FamBudget> list=this.list(lambdaQueryWrapper);

        for (FamBudget famBudget : list) {
            UserQueryForm userQueryForm=new UserQueryForm();
            userQueryForm.setUserId(famBudget.getUserId());
            List<UserQueryForm> userlist= Convert.convert(new TypeReference<List<UserQueryForm>>(){},accountService8001.queryUserList(userQueryForm).getData());
            famBudget.setUserId(userlist.get(0).getUserName());
        }

        return list;
    }
}





