package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.FamBudgetForm;
import com.yyr.pojo.FamBudget;
import com.yyr.service.FamBudgetService;
import com.yyr.mapper.FamBudgetMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    private FamBudgetMapper famBudgetMapper;

    @Override
    public void addFamBudget(FamBudgetForm form) {
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
        if(form.getCreatedTime()!=null ){
            lambdaQueryWrapper.between(FamBudget::getCreatedTime,form.getStartTime(),form.getEndTime());
        }

        return this.list(lambdaQueryWrapper);
    }
}





