package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.SysRoleQueryForm;
import com.yyr.pojo.FamilyRole;
import com.yyr.pojo.SysRole;
import com.yyr.service.SysRoleService;
import com.yyr.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【sys_role(用户系统角色)】的数据库操作Service实现
* @createDate 2022-11-29 12:16:21
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    /**
    * @description:新增系统角色
    * @Param: [sysRole]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    public void addSysRole(SysRole sysRole) {
        Assert.isTrue(sysRole.getSysRoleName()!=null);
        sysRoleMapper.insert(sysRole);
    }

    @Override
    /**
    * @description:删除系统角色
    * @Param: [id]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    public void deleteSysRole(String id) {
        SysRole role=sysRoleMapper.selectById(id);
        Assert.isTrue(role!=null,"该用户不存在！");
        sysRoleMapper.deleteById(id);

    }

    @Override
    /**
    * @description:更新系统角色
    * @Param: [sysRole]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    public void updateSysRole(SysRole sysRole) {
        SysRole role=sysRoleMapper.selectById(sysRole.getSysRoleId());
        Assert.isTrue(role!=null,"该用户不存在！");
        LambdaUpdateWrapper<SysRole> updateWrapper= new UpdateWrapper<SysRole>().lambda();
        updateWrapper.eq(SysRole::getSysRoleId,sysRole.getSysRoleId());
        if(sysRole.getSysRoleName()!=null&&sysRole.getSysRoleName().length()!=0){
            updateWrapper.set(SysRole::getSysRoleName,sysRole.getSysRoleName());
        }
        if(sysRole.getSysRoleDesc()!=null&&sysRole.getSysRoleDesc().length()!=0){
            updateWrapper.set(SysRole::getSysRoleDesc,sysRole.getSysRoleDesc());
        }

        this.update(updateWrapper);

    }

    @Override
    /**
    * @description:查询系统角色
    * @Param: [sysRole]
    * @return: java.util.List<com.yyr.pojo.SysRole>
    * @throws:
    * @author:杨亚茹
    */
    public List<SysRole> querySysRole(SysRoleQueryForm sysRole) {
        LambdaQueryWrapper<SysRole> queryWrapper=new LambdaQueryWrapper<>();
        if(sysRole.getSysRoleDesc()!= null && sysRole.getSysRoleDesc().length()!=0){
            queryWrapper.like(SysRole::getSysRoleDesc,sysRole.getSysRoleDesc());
        }
        if(sysRole.getSysRoleName()!=null && sysRole.getSysRoleName().length()!=0){
            queryWrapper.like(SysRole::getSysRoleName,sysRole.getSysRoleName());
        }
        if(sysRole.getSysRoleId()!= null && sysRole.getSysRoleId().length()!=0){
            queryWrapper.eq(SysRole::getSysRoleId,sysRole.getSysRoleId());
        }
        if(sysRole.getCreatedBy()!=null && sysRole.getCreatedBy().length()!=0){
            queryWrapper.eq(SysRole::getCreatedBy,sysRole.getCreatedBy());
        }

        return this.list(queryWrapper);
    }
}




