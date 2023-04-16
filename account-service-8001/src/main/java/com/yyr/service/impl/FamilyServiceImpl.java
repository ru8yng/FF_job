package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.FamQueryForm;
import com.yyr.pojo.Family;
import com.yyr.service.FamilyService;
import com.yyr.mapper.FamilyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【family(家庭表)】的数据库操作Service实现
* @createDate 2022-11-29 12:15:47
*/
@Service
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family>
    implements FamilyService{

    @Autowired
    private FamilyMapper familyMapper;

    /**
    * @description:新增家庭
    * @Param: [fm]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void addFamily(Family fm) {
        Assert.isTrue(fm.getFamilyName()!=null,"家庭名不为空");
        Family family=new Family();
        if(fm.getFamilyName()!=null &&fm.getFamilyName().length()!=0){
            family.setFamilyName(fm.getFamilyName());
        }
        if(fm.getFamilyDesc()!=null &&fm.getFamilyDesc().length()!=0){
            family.setFamilyDesc(fm.getFamilyDesc());
        }
        if(fm.getCreatedBy()!=null &&fm.getCreatedBy().length()!=0){
            family.setCreatedBy(fm.getCreatedBy());
        }

        if(fm.getUpdatedBy()!=null &&fm.getUpdatedBy().length()!=0){
            family.setUpdatedBy(fm.getUpdatedBy());
        }
        familyMapper.insert(family);
    }

    /**
    * @description:删除家庭
    * @Param: [id]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void deleteFamily(String id) {
        Family fm=familyMapper.selectById(id);
        Assert.notNull(fm,"该家庭不存在");
        familyMapper.deleteById(id);
    }

    /**
    * @description:更新家庭
    * @Param: [fm]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void updateFamily(Family fm) {
        Family family=familyMapper.selectById(fm.getFamilyId());
        Assert.notNull(family,"该家庭不存在！");

        LambdaUpdateWrapper<Family> updateWrapper=new UpdateWrapper<Family>().lambda();

        updateWrapper.eq(Family::getFamilyId,fm.getFamilyId());

        if(fm.getFamilyName()!=null && fm.getFamilyName().length()!=0){
            updateWrapper.set(Family::getFamilyName,fm.getFamilyName());
        }
        if(fm.getFamilyDesc()!=null && fm.getFamilyDesc().length()!=0){
            updateWrapper.set(Family::getFamilyDesc,fm.getFamilyDesc());
        }
        if(fm.getUpdatedBy()!=null && fm.getUpdatedBy().length()!=0){
            updateWrapper.set(Family::getUpdatedBy,fm.getUpdatedBy());
        }

        this.update(updateWrapper);

    }

    /**
    * @description:查询家庭
     * @Param: [form]
    * @return: java.util.List<com.yyr.pojo.Family>
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public List<Family> queryList(FamQueryForm form) {
        LambdaQueryWrapper<Family> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getFamilyId()!=null && form.getFamilyId().length()!=0){
            queryWrapper.eq(Family::getFamilyId,form.getFamilyId());
        }
        if(form.getFamilyName()!=null && form.getFamilyName().length()!=0){
            queryWrapper.like(Family::getFamilyName,form.getFamilyName());
        }
        if(form.getStatus()!=null && form.getStatus().length()!=0){
            queryWrapper.eq(Family::getStatus,form.getStatus());
        }
        if(form.getCreatedBy()!=null && form.getCreatedBy().length()!=0){
            queryWrapper.like(Family::getCreatedBy,form.getCreatedBy());
        }
        queryWrapper.orderByAsc(Family::getFamilyId);

        return this.list(queryWrapper);


    }

    /**
    * @description:启停家庭
    * @Param: [fm_id, status]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void enableFamily(FamQueryForm form) {
        Assert.isTrue(form.getFamilyId()!=null && form.getStatus()!=null,"启停家庭id及状态不为空！");
        Family fm= familyMapper.selectById(form.getFamilyId());
        Assert.notNull(fm,"该家庭不存在！");
        fm.setStatus(form.getStatus());
        familyMapper.updateById(fm);
    }
}




