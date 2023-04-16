package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.FamIncomeForm;
import com.yyr.pojo.FamExpense;
import com.yyr.pojo.FamIncome;
import com.yyr.service.FamIncomeService;
import com.yyr.mapper.FamIncomeMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_income(家庭收入)】的数据库操作Service实现
* @createDate 2023-03-04 14:59:35
*/
@Service
public class FamIncomeServiceImpl extends ServiceImpl<FamIncomeMapper, FamIncome>
implements FamIncomeService{

    @Autowired
    private FamIncomeMapper famIncomeMapper;

    @Override
    public void addFamIncome(FamIncomeForm form) {
        FamIncome famIncome=new FamIncome();
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            famIncome.setCreatedBy(form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            famIncome.setUpdatedBy(form.getUpdatedBy());
        }
        if(form.getUserId()!=null &&form.getUserId().length()!=0){
            famIncome.setUserId(form.getUserId());
        }
        if(form.getFamIncomeTime()!=null){
            famIncome.setFamIncomeTime(form.getFamIncomeTime());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            famIncome.setFamId(form.getFamId());
        }
        if(form.getFamIncomeAmount()!=null &&form.getFamIncomeAmount().length()!=0){
            //校验字符串是否是数字
            Assert.isTrue(StringUtils.isNumeric(form.getFamIncomeAmount()));

            famIncome.setFamIncomeAmount(form.getFamIncomeAmount());
        }
        if(form.getFamIncomeTypeId()!=null &&form.getFamIncomeTypeId().length()!=0){
            famIncome.setFamIncomeTypeId(form.getFamIncomeTypeId());
        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            famIncome.setRemark(form.getRemark());
        }

        famIncomeMapper.insert(famIncome);
    }

    @Override
    public void deleteFamIncom(String famIncomeId) {
        FamIncome famIncome=famIncomeMapper.selectById(famIncomeId);
        Assert.notNull(famIncome,"该家庭收入不存在");
        this.removeById(famIncomeId);
    }

    @Override
    public void updateFamIncome(FamIncomeForm form) {
        FamIncome famIncome=famIncomeMapper.selectById(form.getFamIncomeId());
        Assert.notNull(famIncome,"该家庭收入不存在");
        LambdaUpdateWrapper<FamIncome> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(FamIncome::getFamIncomeId,form.getFamIncomeId());
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            updateWrapper.set(FamIncome::getCreatedBy,form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            updateWrapper.set(FamIncome::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getUserId()!=null &&form.getUserId().length()!=0){
            updateWrapper.set(FamIncome::getUserId,form.getUserId());
        }
        if(form.getFamIncomeTime()!=null){
            updateWrapper.set(FamIncome::getFamIncomeTime,form.getFamIncomeTime());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            updateWrapper.set(FamIncome::getFamId,form.getFamId());
        }
        if(form.getFamIncomeAmount()!=null &&form.getFamIncomeAmount().length()!=0){
            //校验字符串是否是数字
            Assert.isTrue(StringUtils.isNumeric(form.getFamIncomeAmount()));

            updateWrapper.set(FamIncome::getFamIncomeAmount,form.getFamIncomeAmount());
        }
        if(form.getFamIncomeTypeId()!=null &&form.getFamIncomeTypeId().length()!=0){
            updateWrapper.set(FamIncome::getFamIncomeTypeId,form.getFamIncomeTypeId());
        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            updateWrapper.set(FamIncome::getRemark,form.getRemark());
        }

        this.update(updateWrapper);
    }

    @Override
    public List<FamIncome> queryFamIncome(FamIncomeForm form) {
        LambdaQueryWrapper<FamIncome> queryWrapper=new LambdaQueryWrapper<>();

        if(form.getFamIncomeId()!=null &&form.getFamIncomeId().length()!=0){
            queryWrapper.eq(FamIncome::getFamIncomeId,form.getFamIncomeId());
        }
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            queryWrapper.eq(FamIncome::getCreatedBy,form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            queryWrapper.eq(FamIncome::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getUserId()!=null &&form.getUserId().length()!=0){
            queryWrapper.eq(FamIncome::getUserId,form.getUserId());
        }
//        if(form.getExpenseTime()!=null){
//            queryWrapper.eq(FamExpense::getExpenseTime,form.getExpenseTime());
//        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            queryWrapper.eq(FamIncome::getFamId,form.getFamId());
        }
        if(form.getFamIncomeAmount()!=null &&form.getFamIncomeAmount().length()!=0){
            //校验字符串是否是数字
            Assert.isTrue(StringUtils.isNumeric(form.getFamIncomeAmount()));

            queryWrapper.eq(FamIncome::getFamIncomeAmount,form.getFamIncomeAmount());
        }
        if(form.getFamIncomeTypeId()!=null &&form.getFamIncomeTypeId().length()!=0){
            queryWrapper.eq(FamIncome::getFamIncomeTypeId,form.getFamIncomeTypeId());
        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            queryWrapper.like(FamIncome::getRemark,form.getRemark());
        }

        if(form.getStartTime()!=null &&form.getEndTime()!=null){
            queryWrapper.between(FamIncome::getFamIncomeTime,form.getStartTime(),form.getEndTime());
        }

        return this.list(queryWrapper);
    }
}