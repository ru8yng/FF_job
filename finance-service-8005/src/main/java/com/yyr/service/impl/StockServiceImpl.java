package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.StockForm;
import com.yyr.dto.StockTypeEnum;
import com.yyr.dto.StockVS;
import com.yyr.dto.StockVSForm;
import com.yyr.pojo.Stock;
import com.yyr.service.StockService;
import com.yyr.mapper.StockMapper;
import com.yyr.service.bp.StockBp;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
        updateWrapper.set(Stock::getStockId,form.getStockId());
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
        this.update(updateWrapper);

    }

    @Override
    public List<Stock> queryStock(StockForm form) {
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
        return null;
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
        stock.setFamId(form.getFmId());

        stockMapper.insert(stock);
    }
}




