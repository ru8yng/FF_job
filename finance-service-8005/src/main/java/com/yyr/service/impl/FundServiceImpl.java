package com.yyr.service.impl;

import account8001.dto.UserQueryForm;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.pojo.Fund;
import com.yyr.service.AccountService8001;
import com.yyr.service.FundService;
import com.yyr.mapper.FundMapper;
import com.yyr.service.bp.FundBp;
import finance8005.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private AccountService8001 accountService8001;

    @Override
    public void addFund(FundForm form) {
        Fund fund=new Fund();
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            fund.setCreatedBy(form.getCreatedBy());
        }
        if(form.getFundCode()!=null &&form.getFundCode().length()!=0){
            fund.setFundCode(form.getFundCode());
        }
        if(form.getUserId()!=null && form.getUserId().length()!=0){
            fund.setUserId(form.getUserId());
        }
        if(form.getFamId()!=null && form.getFamId().length()!=0){
            fund.setFamId(form.getFamId());
        }
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
        if(form.getCurrentProfit()!=null){
            fund.setCurrentProfit(form.getCurrentProfit());
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
        updateWrapper.eq(Fund::getFundId,form.getFundId());
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            updateWrapper.set(Fund::getFundId,form.getCreatedBy());
        }
        if(form.getFundCode()!=null &&form.getFundCode().length()!=0){
            updateWrapper.set(Fund::getFundCode,form.getFundCode());
        }
        if(form.getUserId()!=null &&form.getUserId().length()!=0){
            updateWrapper.set(Fund::getUserId,form.getUserId());
        }

        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            updateWrapper.set(Fund::getFamId,form.getFamId());
        }

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
        if(form.getCurrentProfit()!=null){
            updateWrapper.set(Fund::getCurrentProfit,form.getCurrentProfit());
        }

        this.update(updateWrapper);
    }

    @Override
    public List<FundForm> queryFund(FundForm form) {
        LambdaQueryWrapper<Fund> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            queryWrapper.eq(Fund::getFundId,form.getCreatedBy());
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
        if(form.getUserId()!=null && form.getUserId().length()!=0){
            queryWrapper.eq(Fund::getUserId,form.getUserId());
        }
        if(form.getFamId()!=null && form.getFamId().length()!=0){
            queryWrapper.eq(Fund::getFamId,form.getFamId());
        }

        List<FundForm> list=new ArrayList<>();
        this.list(queryWrapper).forEach(fund -> {
            UserQueryForm userQueryForm=new UserQueryForm();
            userQueryForm.setUserId(fund.getUserId());
            List<UserQueryForm> list1= Convert.convert(new TypeReference<List<UserQueryForm>>(){},accountService8001.queryUserList(userQueryForm).getData());

            FundForm form1=new FundForm();
            BeanUtils.copyProperties(fund,form1);
            form1.setUserId(list1.get(0).getUserName());
            HistoricalFundNetValue value=queryHistoricalFundNetValueByCode(fund.getFundCode());
            CurrentFundNetValue value1=queryCurrentFundNetValueByCode(fund.getFundCode());
            form1.setRate(value.getFund_Rate());
            form1.setSourceRate(value.getFund_sourceRate());
            form1.setCurrentNetValue(value1.getDwjz());
            list.add(form1);
        });
        return list;
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
    public void collectFund(FundCollectForm form) {
        FundForm form1=new FundForm();
        form1.setFundCode(form.getFundCode());
        Assert.isTrue(queryFund(form1)!=null,"未查询到该基金信息");
        Fund fund=new Fund();
        fund.setFundCode(form.getFundCode());
        HistoricalFundNetValue historical=queryHistoricalFundNetValueByCode(form.getFundCode());
        CurrentFundNetValue value=queryCurrentFundNetValueByCode(form.getFundCode());
        fund.setCurrentNetValue(value.getDwjz());
        fund.setFundName(value.getName());
        fund.setSourceRate(historical.getFund_sourceRate());
        fund.setRate(historical.getFund_Rate());
        fund.setFamId(form.getFamId());
        fund.setUserId(form.getUserId());
        fund.setAmount(new BigDecimal(0));
        fund.setCurrentProfit(new BigDecimal(0));

        fundMapper.insert(fund);

    }

    @Override
    public List<List<String>> getKLine(Date date) {
        return fundBp.getKLine(date);

    }

    @Scheduled(cron = "0 30 22 ? * *")
    void updateProfits() {
        List<FundForm> list = queryFund(new FundForm());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(fund -> {
                FundForm fundForm=new FundForm();
                CurrentFundNetValue currentFundNetValue = queryCurrentFundNetValueByCode(fund.getFundCode());
                BigDecimal profit = fund.getAmount().add(fund.getCurrentProfit()).multiply(currentFundNetValue.getDwjz()).subtract(fund.getAmount());
                BeanUtils.copyProperties(fund,fundForm);
                fund.setCurrentNetValue(currentFundNetValue.getDwjz());
                fundForm.setCurrentProfit(profit);
                updateFund(fundForm);
            });
        }
    }
}




