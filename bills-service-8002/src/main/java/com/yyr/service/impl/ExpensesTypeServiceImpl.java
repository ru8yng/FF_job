package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.ExpenseTypeForm;
import com.yyr.pojo.ExpensesType;
import com.yyr.pojo.FamExpense;
import com.yyr.service.ExpensesTypeService;
import com.yyr.mapper.ExpensesTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【expenses_type(支出类型)】的数据库操作Service实现
* @createDate 2023-03-04 14:58:41
*/
@Service
public class ExpensesTypeServiceImpl extends ServiceImpl<ExpensesTypeMapper, ExpensesType>
implements ExpensesTypeService{

    @Autowired
    private ExpensesTypeMapper expensesTypeMapper;

    @Override
    public void addExpensesType(ExpenseTypeForm form) {
        ExpensesType type=new ExpensesType();

        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            type.setCreatedBy(form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            type.setUpdatedBy(form.getUpdatedBy());
        }
        if(form.getExpenseTypeName()!=null &&form.getExpenseTypeName().length()!=0){
            type.setExpenseTypeName(form.getExpenseTypeName());
        }
        if(form.getExpenseTypeDesc()!=null &&form.getExpenseTypeDesc().length()!=0){
            type.setExpenseTypeDesc(form.getExpenseTypeDesc());
        }

        expensesTypeMapper.insert(type);

    }

    @Override
    public void deleteExpensesType(String expenseTypeId) {
        ExpensesType type=expensesTypeMapper.selectById(expenseTypeId);
        Assert.notNull(type,"该支出类型不存在");
        this.removeById(expenseTypeId);

    }

    @Override
    public void updateExpensesType(ExpenseTypeForm form) {
        ExpensesType type=expensesTypeMapper.selectById(form.getExpenseTypeId());
        Assert.notNull(type,"该支出类型不存在");
        LambdaUpdateWrapper<ExpensesType> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(ExpensesType::getExpenseTypeId,form.getExpenseTypeId());
//        if(form.getExpenseTypeId()!=null &&form.getExpenseTypeId().length()!=0){
//            updateWrapper.set(ExpensesType::getExpenseTypeId,form.getExpenseTypeId());
//        }
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            updateWrapper.set(ExpensesType::getCreatedBy,form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            updateWrapper.set(ExpensesType::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getExpenseTypeName()!=null &&form.getExpenseTypeName().length()!=0){
            updateWrapper.set(ExpensesType::getExpenseTypeName,form.getExpenseTypeName());
        }
        if(form.getExpenseTypeDesc()!=null &&form.getExpenseTypeDesc().length()!=0){
            updateWrapper.set(ExpensesType::getExpenseTypeDesc,form.getExpenseTypeDesc());
        }
        this.update(updateWrapper);
    }

    @Override
    public List<ExpensesType> queryExpensesTypes(ExpenseTypeForm form) {
        LambdaQueryWrapper<ExpensesType> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getExpenseTypeId()!=null &&form.getExpenseTypeId().length()!=0) {
            queryWrapper.eq(ExpensesType::getExpenseTypeId,form.getExpenseTypeId());
        }
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            queryWrapper.eq(ExpensesType::getCreatedBy,form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            queryWrapper.eq(ExpensesType::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getExpenseTypeName()!=null &&form.getExpenseTypeName().length()!=0){
            queryWrapper.like(ExpensesType::getExpenseTypeName,form.getExpenseTypeName());
        }
        if(form.getExpenseTypeDesc()!=null &&form.getExpenseTypeDesc().length()!=0){
            queryWrapper.like(ExpensesType::getExpenseTypeDesc,form.getExpenseTypeDesc());
        }
        if(form.getStartTime()!=null &&form.getEndTime()!=null) {
            queryWrapper.between(ExpensesType::getCreatedTime,form.getStartTime(),form.getEndTime());
        }
            return this.list(queryWrapper);
    }
}
