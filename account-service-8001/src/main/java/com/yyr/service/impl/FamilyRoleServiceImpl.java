package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.FamRoleQueryForm;
import com.yyr.mapper.FamRolePermissionMapper;
import com.yyr.pojo.FamRolePermission;
import com.yyr.pojo.FamilyRole;
import com.yyr.service.FamRolePermissionService;
import com.yyr.service.FamilyRoleService;
import com.yyr.mapper.FamilyRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【family_role(用户家庭角色)】的数据库操作Service实现
* @createDate 2022-11-29 12:15:54
*/
@Service
public class FamilyRoleServiceImpl extends ServiceImpl<FamilyRoleMapper, FamilyRole>
    implements FamilyRoleService{
    @Autowired
    private FamilyRoleMapper familyRoleMapper;
    @Autowired
    private FamRolePermissionService famRolePermissionService;

    /**
    * @description:新增家庭角色
    * @Param: [familyRole]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void addFamRole(FamilyRole familyRole) {
        Assert.isTrue(familyRole.getFamId()!=null && familyRole.getFamRoleName()!=null,"家庭id且角色名不为空");
        familyRoleMapper.insert(familyRole);
    }

    /**
    * @description:删除家庭角色
    * @Param: [id]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void deleteFamRole(String id) {
        FamilyRole familyRole=familyRoleMapper.selectById(id);
        Assert.notNull(familyRole,"该家庭角色不存在");
        familyRoleMapper.deleteById(id);
        famRolePermissionService.deleteFamRolePermissionByFamRoleId(id);
    }

    /**
    * @description:更新家庭角色
    * @Param: [familyRole]
    * @return: com.yyr.pojo.FamilyRole
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void updateFamRole(FamilyRole familyRole) {
        FamilyRole familyRole1=familyRoleMapper.selectById(familyRole.getFamId());
        Assert.notNull(familyRole1,"该家庭角色不存在");

        LambdaUpdateWrapper<FamilyRole> updateWrapper= new UpdateWrapper<FamilyRole>().lambda();

        updateWrapper.eq(FamilyRole::getFamRoleId,familyRole.getFamRoleId());

        if(familyRole.getFamRoleDesc()!= null && familyRole.getFamRoleDesc().length()!=0){
            updateWrapper.set(FamilyRole::getFamRoleDesc,familyRole.getFamRoleDesc());
        }
        if(familyRole.getFamRoleName()!=null && familyRole.getFamRoleName().length()!=0){
            updateWrapper.set(FamilyRole::getFamRoleName,familyRole.getFamRoleName());
        }
        this.update(updateWrapper);
    }

    /**
    * @description:查询家庭角色
    * @Param: [familyRole]
    * @return: com.yyr.pojo.FamilyRole
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public List<FamilyRole> queryFamRoleList(FamRoleQueryForm familyRole) {
        LambdaQueryWrapper<FamilyRole> queryWrapper=new LambdaQueryWrapper<>();
        if(familyRole.getFamRoleDesc()!= null && familyRole.getFamRoleDesc().length()!=0){
            queryWrapper.like(FamilyRole::getFamRoleDesc,familyRole.getFamRoleDesc());
        }
        if(familyRole.getFamRoleName()!=null && familyRole.getFamRoleName().length()!=0){
            queryWrapper.like(FamilyRole::getFamRoleName,familyRole.getFamRoleName());
        }
        if(familyRole.getFamRoleId()!= null && familyRole.getFamRoleId().length()!=0){
            queryWrapper.eq(FamilyRole::getFamRoleId,familyRole.getFamId());
        }
        if(familyRole.getFamId()!=null && familyRole.getFamId().length()!=0){
            queryWrapper.eq(FamilyRole::getFamId,familyRole.getFamId());
        }
        if(familyRole.getCreatedBy()!=null && familyRole.getCreatedBy().length()!=0){
            queryWrapper.eq(FamilyRole::getCreatedBy,familyRole.getCreatedBy());
        }
        return this.list(queryWrapper);
    }
}




