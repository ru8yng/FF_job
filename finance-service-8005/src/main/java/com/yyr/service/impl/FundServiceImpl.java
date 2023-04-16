package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.CurrentFundNetValue;
import com.yyr.dto.FundForm;
import com.yyr.dto.FundcodeSearch;
import com.yyr.dto.HistoricalFundNetValue;
import com.yyr.pojo.Fund;
import com.yyr.service.FundService;
import com.yyr.mapper.FundMapper;
import com.yyr.service.bp.FundBp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【fund(收藏基金)】的数据库操作Service实现
* @createDate 2023-03-28 09:33:58
*/
@Service
public class FundServiceImpl extends ServiceImpl<FundMapper, Fund>
    implements FundService{

    @Autowired
    private FundMapper fundMapper;

    @Autowired
    private FundBp fundBp;

    @Override
    public void addFund(FundForm form) {
        Fund fund=new Fund();
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            fund.setCreatedBy(form.getCreatedBy());
        }
        if(form.getFundCode()!=null &&form.getFundCode().length()!=0){
            fund.setFundCode(form.getFundCode());
        }
//        if(form.getCurrentProfit()!=null){
//            fund.setCurrentProfit(form.getCurrentProfit());
//        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            fund.setUpdatedBy(form.getUpdatedBy());
        }
        if(form.getFundName()!=null && form.getFundName().length()!=0){
            fund.setFundName(form.getFundName());
        }
        if(form.getFundType()!=null && form.getFundType().length()!=0){
            fund.setFundType(form.getFundType());
        }
        if(form.getCurrentNetValue()!=null ){
            fund.setCurrentNetValue(form.getCurrentNetValue());
        }
        if(form.getSourceRate()!=null ){
            fund.setSourceRate(form.getSourceRate());
        }
        if(form.getRate()!=null ){
            fund.setRate(form.getRate());
        }
        if(form.getMillionCopiesIncome()!=null ){
            fund.setMillionCopiesIncome(form.getMillionCopiesIncome());
        }
        if(form.getAmount()!=null){
            fund.setAmount(form.getAmount());
        }

        fundMapper.insert(fund);
    }

    @Override
    public void deleteFund(String fundId) {
        Fund fund=fundMapper.selectById(fundId);
        Assert.notNull(fund,"该收藏基金不存在");
        this.removeById(fundId);

    }

    @Override
    public void updateFund(FundForm form) {
        Fund fund=fundMapper.selectById(form.getFundId());
        Assert.notNull(fund,"该收藏基金不存在");
        LambdaUpdateWrapper<Fund> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.set(Fund::getFund_id,form.getFundId());
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            updateWrapper.set(Fund::getFund_id,form.getCreatedBy());
        }
        if(form.getFundCode()!=null &&form.getFundCode().length()!=0){
            updateWrapper.set(Fund::getFundCode,form.getFundCode());
        }
//        if(form.getCurrentProfit()!=null){
//            updateWrapper.set(Fund::getCurrentProfit,form.getCurrentProfit());
//        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            updateWrapper.set(Fund::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getFundName()!=null && form.getFundName().length()!=0){
            updateWrapper.set(Fund::getFundName,form.getFundName());
        }
        if(form.getFundType()!=null && form.getFundType().length()!=0){
            updateWrapper.set(Fund::getFundType,form.getFundType());
        }
        if(form.getCurrentNetValue()!=null ){
            updateWrapper.set(Fund::getCurrentNetValue,form.getCurrentNetValue());
        }
        if(form.getSourceRate()!=null ){
            updateWrapper.set(Fund::getSourceRate,form.getSourceRate());
        }
        if(form.getRate()!=null ){
            updateWrapper.set(Fund::getRate,form.getRate());
        }
        if(form.getMillionCopiesIncome()!=null ){
            updateWrapper.set(Fund::getMillionCopiesIncome,form.getMillionCopiesIncome());
        }
        if(form.getAmount()!=null){
            updateWrapper.set(Fund::getAmount,form.getAmount());
        }

        this.update(updateWrapper);
    }

    @Override
    public List<Fund> queryFund(FundForm form) {
        LambdaQueryWrapper<Fund> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            queryWrapper.eq(Fund::getFund_id,form.getCreatedBy());
        }
        if(form.getFundCode()!=null &&form.getFundCode().length()!=0){
            queryWrapper.like(Fund::getFundCode,form.getFundCode());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            queryWrapper.eq(Fund::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getFundName()!=null && form.getFundName().length()!=0){
            queryWrapper.like(Fund::getFundName,form.getFundName());
        }
        if(form.getFundType()!=null && form.getFundType().length()!=0){
            queryWrapper.eq(Fund::getFundType,form.getFundType());
        }
        return this.list(queryWrapper);
    }

    @Override
    public CurrentFundNetValue queryCurrentFundNetValueByCode(String fundCode) {
        return fundBp.getCurrentFundNetValue(fundCode);
    }

    @Override
    public HistoricalFundNetValue queryHistoricalFundNetValueByCode(String fundCode) {
        return fundBp.getHistoricalFundNetValue(fundCode);
    }

    @Override
    public List<FundcodeSearch> queryFundcodeSearch() {
        return fundBp.getAllFund();
    }

    @Override
    public void collectFund(String fundCode) {
        FundForm form=new FundForm();
        form.setFundCode(fundCode);
        Assert.isTrue(queryFund(form)!=null,"");
        Fund fund=new Fund();
        fund.setFundCode(fundCode);
        HistoricalFundNetValue historical=queryHistoricalFundNetValueByCode(fundCode);
        CurrentFundNetValue value=queryCurrentFundNetValueByCode(fundCode);
        fund.setCurrentNetValue(value.getDwjz());
        fund.setFundName(value.getName());
        fund.setSourceRate(historical.getFund_sourceRate());
        fund.setRate(historical.getFund_Rate());

        fundMapper.insert(fund);

    }
}




