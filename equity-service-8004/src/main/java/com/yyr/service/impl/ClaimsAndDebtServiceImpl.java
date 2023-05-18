package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.pojo.ClaimsAndDebt;
import com.yyr.service.ClaimsAndDebtService;
import com.yyr.mapper.ClaimsAndDebtMapper;
import equity8004.dto.ClaimsAndDebtForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【claims_and_debt(借贷)】的数据库操作Service实现
* @createDate 2023-03-04 15:03:23
*/
@Service
public class ClaimsAndDebtServiceImpl extends ServiceImpl<ClaimsAndDebtMapper, ClaimsAndDebt>
implements ClaimsAndDebtService{

    @Autowired
    private ClaimsAndDebtMapper claimsAndDebtMapper;
    @Override
    public void addCAD(ClaimsAndDebtForm form) {
        ClaimsAndDebt cad=new ClaimsAndDebt();
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            cad.setCreatedBy(form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            cad.setUpdatedBy(form.getUpdatedBy());
        }
        if(form.getCadType()!=null &&form.getCadType().length()!=0){
            cad.setCadType(form.getCadType());
        }
        if(form.getCreditor()!=null &&form.getCreditor().length()!=0){
            cad.setCreditor(form.getCreditor());
        }
        if(form.getObligor()!=null&& form.getObligor().length()!=0){
            cad.setObligor(form.getObligor());
        }
        if(form.getCreditorTel()!=null &&form.getCreditorTel().length()!=0){
            cad.setCreditorTel(form.getCreditorTel());
        }
        if(form.getObligorTel()!=null &&form.getObligorTel().length()!=0){
            cad.setObligorTel(form.getObligorTel());
        }
        if(form.getCadAmount()!=null &&form.getCadAmount().length()!=0){
            cad.setCadAmount(form.getCadAmount());
        }
        if(form.getCadTime()!=null){
            cad.setCadTime(form.getCadTime());
        }
        if(form.getCadRepaymentTime()!=null){
            cad.setCadRepaymentTime(form.getCadRepaymentTime());
        }
        if(form.getCadPlanRepaymentTime()!=null){
            cad.setCadPlanRepaymentTime(form.getCadPlanRepaymentTime());

        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            cad.setRemark(form.getRemark());
        }

        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            cad.setFamId(form.getFamId());
        }
        cad.setCadStatus("1");

        claimsAndDebtMapper.insert(cad);
    }

    @Override
    public void deleteCAD(String cadId) {
        ClaimsAndDebt cad=claimsAndDebtMapper.selectById(cadId);
        Assert.notNull(cad,"该借贷关系不存在");
        this.removeById(cadId);
    }

    @Override
    public void updateCAD(ClaimsAndDebtForm form) {
        ClaimsAndDebt cad=claimsAndDebtMapper.selectById(form.getCadId());
        Assert.notNull(cad,"该借贷不存在");
        LambdaUpdateWrapper<ClaimsAndDebt> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(ClaimsAndDebt::getCadId,form.getCadId());



        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            updateWrapper.set(ClaimsAndDebt::getCreatedBy,form.getCreatedBy());
        }
        if(form.getCadType()!=null &&form.getCadType().length()!=0){
            updateWrapper.set(ClaimsAndDebt::getCadType,form.getCadType());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            updateWrapper.set(ClaimsAndDebt::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getCreditor()!=null &&form.getCreditor().length()!=0){
            updateWrapper.set(ClaimsAndDebt::getCreditor,form.getCreditor());
        }
        if(form.getObligor()!=null &&form.getObligor().length()!=0){
            updateWrapper.set(ClaimsAndDebt::getObligor,form.getObligor());
        }
        if(form.getCreditorTel()!=null &&form.getCreditorTel().length()!=0){
            updateWrapper.set(ClaimsAndDebt::getCreditorTel,form.getCreditorTel());
        }
        if(form.getObligorTel()!=null &&form.getObligorTel().length()!=0){
            updateWrapper.set(ClaimsAndDebt::getObligorTel,form.getObligorTel());
        }
        if(form.getCadTime()!=null ){
            updateWrapper.set(ClaimsAndDebt::getCadTime,form.getCadTime());
        }
        if(form.getCadAmount()!=null &&form.getCadAmount().length()!=0){
            updateWrapper.set(ClaimsAndDebt::getCadAmount,form.getCadAmount());
        }
        if(form.getCadRepaymentTime()!=null){
            updateWrapper.set(ClaimsAndDebt::getCadRepaymentTime,form.getCadRepaymentTime());
        }
        if(form.getCadPlanRepaymentTime()!=null ){

            updateWrapper.set(ClaimsAndDebt::getCadPlanRepaymentTime,form.getCadPlanRepaymentTime());
        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            updateWrapper.set(ClaimsAndDebt::getRemark,form.getRemark());
        }
        if(form.getCadStatus()!=null &&form.getCadStatus().length()!=0){
            updateWrapper.set(ClaimsAndDebt::getCadStatus,form.getCadStatus());
        }
        this.update(updateWrapper);
    }

    @Override
    public List<ClaimsAndDebt> queryCAD(ClaimsAndDebtForm form) {
        LambdaQueryWrapper<ClaimsAndDebt> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getCadId()!=null &&form.getCadId().length()!=0){
            queryWrapper.eq(ClaimsAndDebt::getCadId,form.getCadId());
        }
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            queryWrapper.eq(ClaimsAndDebt::getCreatedBy,form.getCreatedBy());
        }
        if(form.getCadType()!=null &&form.getCadType().length()!=0){
            queryWrapper.eq(ClaimsAndDebt::getCadType,form.getCadType());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            queryWrapper.eq(ClaimsAndDebt::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getCreditor()!=null &&form.getCreditor().length()!=0){
            queryWrapper.like(ClaimsAndDebt::getCreditor,form.getCreditor());
        }
        if(form.getObligor()!=null &&form.getObligor().length()!=0){
            queryWrapper.like(ClaimsAndDebt::getObligor,form.getObligor());
        }
        if(form.getCreditorTel()!=null &&form.getCreditorTel().length()!=0){
            queryWrapper.eq(ClaimsAndDebt::getCreditorTel,form.getCreditorTel());
        }
        if(form.getObligorTel()!=null &&form.getObligorTel().length()!=0){
            queryWrapper.eq(ClaimsAndDebt::getObligorTel,form.getObligorTel());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            queryWrapper.eq(ClaimsAndDebt::getFamId,form.getFamId());
        }

        if(form.getCadTime()!=null ){
            queryWrapper.eq(ClaimsAndDebt::getCadTime,form.getCadTime());
        }
        if(form.getCadAmount()!=null &&form.getCadAmount().length()!=0){
            queryWrapper.eq(ClaimsAndDebt::getCadAmount,form.getCadAmount());
        }
        if(form.getCadRepaymentTime()!=null){
            queryWrapper.eq(ClaimsAndDebt::getCadRepaymentTime,form.getCadRepaymentTime());
        }
        if(form.getCadPlanRepaymentTime()!=null ){

            queryWrapper.eq(ClaimsAndDebt::getCadPlanRepaymentTime,form.getCadPlanRepaymentTime());
        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            queryWrapper.eq(ClaimsAndDebt::getRemark,form.getRemark());
        }

        List<ClaimsAndDebt> list=this.list(queryWrapper);
        for (ClaimsAndDebt claimsAndDebt : list) {
            if (claimsAndDebt.getCadStatus().equals("0")){
                claimsAndDebt.setCadStatus("已完成");
            }else{
                claimsAndDebt.setCadStatus("未完成");
            }
        }

        return list;
    }
}
