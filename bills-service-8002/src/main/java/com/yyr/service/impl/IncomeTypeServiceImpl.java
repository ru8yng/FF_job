package com.yyr.service.impl;

import bills8002.dto.IncomeTypeForm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.pojo.ExpensesType;
import com.yyr.pojo.IncomeType;
import com.yyr.service.IncomeTypeService;
import com.yyr.mapper.IncomeTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【income_type(收入类型)】的数据库操作Service实现
* @createDate 2023-03-04 15:00:00
*/
@Service
public class IncomeTypeServiceImpl extends ServiceImpl<IncomeTypeMapper, IncomeType>
implements IncomeTypeService{

    @Autowired
    private IncomeTypeMapper incomeTypeMapper;
    @Override
    public void addIncomeType(IncomeTypeForm form) {
        IncomeType type=new IncomeType();

        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            type.setCreatedBy(form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            type.setUpdatedBy(form.getUpdatedBy());
        }
        if(form.getIncomeTypeName()!=null &&form.getIncomeTypeName().length()!=0){
            type.setIncomeTypeName(form.getIncomeTypeName());
        }
        if(form.getIncomeTypeDesc()!=null &&form.getIncomeTypeDesc().length()!=0){
            type.setIncomeTypeDesc(form.getIncomeTypeDesc());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            type.setFamId(form.getFamId());
        }

        incomeTypeMapper.insert(type);
    }

    @Override
    public void deleteIncomeType(String incomeTypeId) {
        IncomeType type=incomeTypeMapper.selectById(incomeTypeId);
        Assert.notNull(type,"该收入类型不存在");
        this.removeById(incomeTypeId);
    }

    @Override
    public void updateIncomeType(IncomeTypeForm form) {
        IncomeType type=incomeTypeMapper.selectById(form.getIncomeTypeId());
        Assert.notNull(type,"该收入类型不存在");
        LambdaUpdateWrapper<IncomeType> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(IncomeType::getIncomeTypeId,form.getIncomeTypeId());

        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            updateWrapper.set(IncomeType::getCreatedBy,form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            updateWrapper.set(IncomeType::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getIncomeTypeName()!=null &&form.getIncomeTypeName().length()!=0){
            updateWrapper.set(IncomeType::getIncomeTypeName,form.getIncomeTypeName());
        }
        if(form.getIncomeTypeDesc()!=null &&form.getIncomeTypeDesc().length()!=0){
            updateWrapper.set(IncomeType::getIncomeTypeDesc,form.getIncomeTypeDesc());
        }
        this.update(updateWrapper);
    }

    @Override
    public List<IncomeType> queryIncomeType(IncomeTypeForm form) {
        LambdaQueryWrapper<IncomeType> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getIncomeTypeId()!=null &&form.getIncomeTypeId().length()!=0) {
            queryWrapper.eq(IncomeType::getIncomeTypeId,form.getIncomeTypeId());
        }
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            queryWrapper.eq(IncomeType::getCreatedBy,form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            queryWrapper.eq(IncomeType::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            queryWrapper.eq(IncomeType::getFamId,form.getFamId());
        }
        if(form.getIncomeTypeName()!=null &&form.getIncomeTypeName().length()!=0){
            queryWrapper.like(IncomeType::getIncomeTypeName,form.getIncomeTypeName());
        }
        if(form.getIncomeTypeDesc()!=null &&form.getIncomeTypeDesc().length()!=0){
            queryWrapper.like(IncomeType::getIncomeTypeDesc,form.getIncomeTypeDesc());
        }
        if(form.getStartTime()!=null &&form.getEndTime()!=null) {
            queryWrapper.between(IncomeType::getCreatedTime,form.getStartTime(),form.getEndTime());
        }

        return this.list(queryWrapper);
    }
}
