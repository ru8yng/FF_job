package com.yyr.service.impl;

import account8001.dto.UserQueryForm;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.pojo.Stock;
import com.yyr.service.AccountService8001;
import com.yyr.service.StockService;
import com.yyr.mapper.StockMapper;
import com.yyr.service.bp.StockBp;
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
* @description 针对表【stock(收藏股票)】的数据库操作Service实现
* @createDate 2023-04-11 14:56:07
*/
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock>
    implements StockService{

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockBp stockBp;

    @Autowired
    private AccountService8001 accountService8001;

    @Override
    public void addStock(StockForm form) {
        Stock stock=new Stock();
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            stock.setCreatedBy(form.getCreatedBy());
        }
        if(form.getStockCode()!=null &&form.getStockCode().length()!=0){
            stock.setStockCode(form.getStockCode());
        }
        if(form.getStockName()!=null &&form.getStockName().length()!=0){
            stock.setStockName(form.getStockName());
        }
        if(form.getStockType()!=null &&form.getStockType().length()!=0){
            stock.setStockType(form.getStockType());
        }
        if(form.getStockPrice()!=null ){
            stock.setStockPrice(form.getStockPrice());
        }
        if(form.getStockNum()!=null ){
            stock.setStockNum(form.getStockNum());
        }
        if(form.getStockTime()!=null ){
            stock.setStockTime(form.getStockTime());
        }
        if(form.getUserId()!=null &&form.getUserId().length()!=0){
            stock.setUserId(form.getUserId());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            stock.setUpdatedBy(form.getUpdatedBy());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            stock.setFamId(form.getFamId());
        }
        if(form.getCurrentProfit()!=null){
            stock.setCurrentProfit(form.getCurrentProfit());
        }
        stockMapper.insert(stock);

    }

    @Override
    public void deleteStock(String stockId) {
        Stock stock=stockMapper.selectById(stockId);
        Assert.notNull(stock,"该收藏股票不存在");
        this.removeById(stockId);
    }

    @Override
    public void updateStock(StockForm form) {
        Stock stock=stockMapper.selectById(form.getStockId());
        Assert.notNull(stock,"该收藏股票不存在");
        LambdaUpdateWrapper<Stock> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(Stock::getStockId,form.getStockId());
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            updateWrapper.set(Stock::getStockId,form.getCreatedBy());
        }
        if(form.getStockCode()!=null &&form.getStockCode().length()!=0){
            updateWrapper.set(Stock::getStockCode,form.getStockCode());
        }
        if(form.getStockName()!=null &&form.getStockName().length()!=0){
            updateWrapper.set(Stock::getStockName,form.getStockName());
        }
        if(form.getStockType()!=null &&form.getStockType().length()!=0){
            updateWrapper.set(Stock::getStockType,form.getStockType());
        }
        if(form.getStockPrice()!=null ){
            updateWrapper.set(Stock::getStockPrice,form.getStockPrice());
        }
        if(form.getStockNum()!=null ){
            updateWrapper.set(Stock::getStockNum,form.getStockNum());
        }
        if(form.getStockTime()!=null ){
            updateWrapper.set(Stock::getStockTime,form.getStockTime());
        }
        if(form.getUserId()!=null &&form.getUserId().length()!=0){
            updateWrapper.set(Stock::getUserId,form.getUserId());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            updateWrapper.set(Stock::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            updateWrapper.set(Stock::getFamId,form.getFamId());
        }
        if(form.getCurrentProfit()!=null){
            updateWrapper.set(Stock::getCurrentProfit,form.getCurrentProfit());
        }
        this.update(updateWrapper);

    }

    @Override
    public List<StockForm> queryStock(StockForm form) {
        LambdaQueryWrapper<Stock> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getStockId()!=null &&form.getStockId().length()!=0){
            queryWrapper.eq(Stock::getStockId,form.getStockId());
        }
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            queryWrapper.eq(Stock::getStockId,form.getCreatedBy());
        }
        if(form.getStockCode()!=null &&form.getStockCode().length()!=0){
            queryWrapper.like(Stock::getStockCode,form.getStockCode());
        }
        if(form.getStockName()!=null &&form.getStockName().length()!=0){
            queryWrapper.like(Stock::getStockName,form.getStockName());
        }
        if(form.getStockType()!=null &&form.getStockType().length()!=0){
            queryWrapper.eq(Stock::getStockType,form.getStockType());
        }
        if(form.getStockTime()!=null ){
            queryWrapper.eq(Stock::getStockTime,form.getStockTime());
        }
        if(form.getUserId()!=null &&form.getUserId().length()!=0){
            queryWrapper.eq(Stock::getUserId,form.getUserId());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            queryWrapper.eq(Stock::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            queryWrapper.eq(Stock::getFamId,form.getFamId());
        }
        List<StockForm> forms=new ArrayList<>();
        this.list(queryWrapper).forEach(stock -> {
            UserQueryForm userQueryForm=new UserQueryForm();
            userQueryForm.setUserId(stock.getUserId());
            List<UserQueryForm> list= Convert.convert(new TypeReference<List<UserQueryForm>>(){},accountService8001.queryUserList(userQueryForm).getData());
            StockForm form1=new StockForm();
            BeanUtils.copyProperties(stock,form1);
            form1.setUserId(list.get(0).getUserName());
            forms.add(form1);
        });
        return forms;
    }

    @Override
    public StockVS queryStockCurrent(StockVSForm form) {
        return stockBp.getStockVS(form);
    }

    @Override
    public void collectStock(StockVSForm form) {
        Stock stock=new Stock();
        StockVS vs=queryStockCurrent(form);
        Assert.notNull(vs,"该股票未查询到信息");
        stock.setStockType(vs.getStock_type());
        stock.setStockCode(form.getStockCode());
        stock.setStockName(vs.getName());
        stock.setStockPrice(vs.getCurrent());
        stock.setStockTime(new Date());
        stock.setUserId(form.getUserId());
        stock.setFamId(form.getFamId());
        stock.setCurrentProfit(new BigDecimal(0));
        stock.setStockNum(new BigDecimal(0));

        stockMapper.insert(stock);
    }

    @Scheduled(cron = "0 30 22 ? * *")
    void updateStockProfits(){
        List<StockForm> forms=queryStock(new StockForm());
        if(!CollectionUtils.isEmpty(forms)){
            forms.forEach(stockForm -> {
                StockVSForm stockVSForm=new StockVSForm();
                stockVSForm.setType(stockForm.getStockType());
                stockVSForm.setStockCode(stockForm.getStockCode());
                StockVS stockVS=queryStockCurrent(stockVSForm);
                BigDecimal profit=stockForm.getStockPrice().subtract(stockVS.getCurrent()).multiply(stockForm.getStockNum());
                stockForm.setCurrentProfit(profit);
                stockForm.setUserId(null);
                updateStock(stockForm);
            });
        }

    }
}




