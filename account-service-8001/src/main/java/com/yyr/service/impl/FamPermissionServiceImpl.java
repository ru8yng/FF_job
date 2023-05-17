package com.yyr.service.impl;

import account8001.dto.FamPermQueryForm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.pojo.FamPermission;
import com.yyr.service.FamPermissionService;
import com.yyr.mapper.FamPermissionMapper;
import com.yyr.service.FamilyRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_permission(家庭权限)】的数据库操作Service实现
* @createDate 2022-11-29 12:15:20
*/
@Service
public class FamPermissionServiceImpl extends ServiceImpl<FamPermissionMapper, FamPermission>
    implements FamPermissionService{
    @Autowired
    private FamPermissionMapper famPermissionMapper;

    @Autowired
    private FamilyRoleService familyRoleService;

    @Override
    /**
    * @description:新增家庭权限
    * @Param: [famPermission]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    public void addFamPermission(FamPermQueryForm famPermission) {
        Assert.isTrue(famPermission.getFamPermissionName()!=null&&famPermission.getFamPermissionPath()!=null,"家庭权限名及路径不为空！");
        famPermission.setFamPermissionId("");
        FamPermission famPermission1=new FamPermission();
        BeanUtils.copyProperties(famPermission,famPermission1);
        famPermissionMapper.insert(famPermission1);
    }

    @Override
    /**
    * @description:删除家庭权限
    * @Param: [FamPermId]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    public void deleteFamPermission(String FamPermId) {
        FamPermission famPermission=famPermissionMapper.selectById(FamPermId);
        Assert.isTrue(famPermission!=null,"此家庭权限不存在！");
        famPermissionMapper.deleteById(FamPermId);
    }

    @Override
    /**
    * @description:更新家庭权限
    * @Param: [famPermission]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    public void updateFamPermission(FamPermQueryForm famPermission) {
        FamPermission famPermission1=famPermissionMapper.selectById(famPermission.getFamPermissionId());
        Assert.notNull(famPermission1,"该家庭权限不存在");

        LambdaUpdateWrapper<FamPermission> updateWrapper= new UpdateWrapper<FamPermission>().lambda();

        updateWrapper.eq(FamPermission::getFamPermissionId,famPermission.getFamPermissionId());

        if(famPermission.getFamPermissionName()!= null && famPermission.getFamPermissionName().length()!=0){
            updateWrapper.set(FamPermission::getFamPermissionName,famPermission.getFamPermissionName());
        }
        if(famPermission.getFamPermissionPath()!=null && famPermission.getFamPermissionPath().length()!=0){
            updateWrapper.set(FamPermission::getFamPermissionPath,famPermission.getFamPermissionPath());
        }
        if(famPermission.getFamPermissionParentid()!=null && famPermission.getFamPermissionParentid().length()!=0){
            updateWrapper.set(FamPermission::getFamPermissionParentid,famPermission.getFamPermissionParentid());
        }
        if(famPermission.getIcon()!=null && famPermission.getIcon().length()!=0){
            updateWrapper.set(FamPermission::getIcon,famPermission.getIcon());
        }
        this.update(updateWrapper);
    }

    @Override
    /**
    * @description:查询家庭权限
    * @Param: [famPermission]
    * @return: java.util.List<com.yyr.pojo.FamPermission>
    * @throws:
    * @author:杨亚茹
    */
    public List<FamPermission> queryFamPermissionList(FamPermQueryForm form) {
        LambdaQueryWrapper<FamPermission> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getFamPermissionId()!= null && form.getFamPermissionId().length()!=0){
            queryWrapper.eq(FamPermission::getFamPermissionId,form.getFamPermissionId());
        }
        if(form.getFamPermissionName()!=null && form.getFamPermissionName().length()!=0){
            queryWrapper.like(FamPermission::getFamPermissionName,form.getFamPermissionName());
        }

        if(form.getFamPermissionParentid()!=null && form.getFamPermissionParentid().length()!=0){
            queryWrapper.eq(FamPermission::getFamPermissionParentid,form.getFamPermissionParentid());
        }
        if(form.getCreatedBy()!=null && form.getCreatedBy().length()!=0){
            queryWrapper.eq(FamPermission::getCreatedBy,form.getCreatedBy());
        }

        List<FamPermission> tree=familyRoleService.buildTree(this.list(queryWrapper));

        return tree;
    }
}




