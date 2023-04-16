package com.yyr.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.FamAssetsForm;
import com.yyr.pojo.FamAssets;
import com.yyr.service.FamAssetsService;
import com.yyr.mapper.FamAssetsMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_assets(固定资产)】的数据库操作Service实现
* @createDate 2023-03-04 14:54:37
*/
@Service
public class FamAssetsServiceImpl extends ServiceImpl<FamAssetsMapper, FamAssets>
implements FamAssetsService{

    @Autowired
    private FamAssetsMapper famAssetsMapper;

    @Override
    public void addAssets(FamAssetsForm form) {
        FamAssets assets=new FamAssets();
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            assets.setCreatedBy(form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            assets.setUpdatedBy(form.getUpdatedBy());
        }
        if(form.getAssetsName()!=null &&form.getAssetsName().length()!=0){
            assets.setAssetsName(form.getAssetsName());
        }
        if(form.getAssetsLocation()!=null &&form.getAssetsLocation().length()!=0){
            assets.setAssetsLocation(form.getAssetsLocation());
        }
        if(form.getAssetsBuytime()!=null){
            assets.setAssetsBuytime(form.getAssetsBuytime());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            assets.setFamId(form.getFamId());
        }
        if(form.getAssetsInstalment()!=null &&form.getAssetsInstalment().length()!=0){
            assets.setAssetsInstalment(form.getAssetsInstalment());
        }
        if(form.getInstalmentPrice()!=null &&form.getInstalmentPrice().length()!=0){
            assets.setInstalmentPrice(form.getInstalmentPrice());
        }
        if(form.getInstalmentSurplus()!=null &&form.getInstalmentSurplus().length()!=0){
            assets.setInstalmentSurplus(form.getInstalmentSurplus());
        }

        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            assets.setRemark(form.getRemark());
        }
        famAssetsMapper.insert(assets);

    }

    @Override
    public void deleteAssets(String assId) {

        FamAssets assets=famAssetsMapper.selectById(assId);
        Assert.notNull(assets,"该家庭资产不存在");
        this.removeById(assId);

    }

    @Override
    public void updateAssets(FamAssetsForm form) {
        FamAssets assets=famAssetsMapper.selectById(form.getFamAssetsId());
        Assert.notNull(assets,"该家庭资产不存在");
        LambdaUpdateWrapper<FamAssets> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(FamAssets::getFamAssetsId,form.getFamAssetsId());
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            updateWrapper.set(FamAssets::getCreatedBy,form.getCreatedBy());
        }
        if(form.getAssetsName()!=null &&form.getAssetsName().length()!=0){
            updateWrapper.set(FamAssets::getAssetsName,form.getAssetsName());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            updateWrapper.set(FamAssets::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getAssetsLocation()!=null &&form.getAssetsLocation().length()!=0){
            updateWrapper.set(FamAssets::getAssetsLocation,form.getAssetsLocation());
        }
        if(form.getAssetsBuytime()!=null ){
            updateWrapper.set(FamAssets::getAssetsBuytime,form.getAssetsBuytime());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            updateWrapper.set(FamAssets::getFamId,form.getFamId());
        }
        if(form.getAssetsInstalment()!=null &&form.getAssetsInstalment().length()!=0){
            updateWrapper.set(FamAssets::getAssetsInstalment,form.getAssetsInstalment());
        }
        if(form.getInstalmentPrice()!=null &&form.getInstalmentPrice().length()!=0){
            Assert.isTrue(StringUtils.isNumeric(form.getInstalmentPrice()));
            updateWrapper.set(FamAssets::getInstalmentPrice,form.getInstalmentPrice());
        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            updateWrapper.set(FamAssets::getRemark,form.getRemark());
        }
        this.update(updateWrapper);

    }

    @Override
    public List<FamAssets> queryAssets(FamAssetsForm form) {
        LambdaQueryWrapper<FamAssets> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getFamAssetsId()!=null &&form.getFamAssetsId().length()!=0) {
            queryWrapper.eq(FamAssets::getFamAssetsId,form.getFamAssetsId());
        }
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            queryWrapper.eq(FamAssets::getCreatedBy,form.getCreatedBy());
        }
        if(form.getAssetsName()!=null &&form.getAssetsName().length()!=0){
            queryWrapper.eq(FamAssets::getAssetsName,form.getAssetsName());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            queryWrapper.eq(FamAssets::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getAssetsLocation()!=null &&form.getAssetsLocation().length()!=0){
            queryWrapper.eq(FamAssets::getAssetsLocation,form.getAssetsLocation());
        }
        if(form.getAssetsBuytime()!=null ){
            queryWrapper.eq(FamAssets::getAssetsBuytime,form.getAssetsBuytime());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            queryWrapper.eq(FamAssets::getFamId,form.getFamId());
        }
        if(form.getAssetsInstalment()!=null &&form.getAssetsInstalment().length()!=0){
            queryWrapper.eq(FamAssets::getAssetsInstalment,form.getAssetsInstalment());
        }
        if(form.getInstalmentPrice()!=null &&form.getInstalmentPrice().length()!=0){
            queryWrapper.eq(FamAssets::getInstalmentPrice,form.getInstalmentPrice());
        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            queryWrapper.like(FamAssets::getRemark,form.getRemark());
        }
        List<FamAssets> list=this.list(queryWrapper);
        list.forEach(ass->{
            long betweenDay = DateUtil.between(ass.getAssetsBuytime(),DateUtil.date(), DateUnit.DAY);
            if(betweenDay>31){
                FamAssetsForm form1=new FamAssetsForm();
                form1.setFamAssetsId(ass.getFamAssetsId());
                form1.setInstalmentSurplus(String.valueOf(Integer.parseInt(ass.getInstalmentSurplus())-1));
                this.updateAssets(form1);
            }
        });
        return list;
    }
}
