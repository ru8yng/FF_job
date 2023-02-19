package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.FamRolePermissionForm;
import com.yyr.pojo.FamRolePermission;
import com.yyr.service.FamRolePermissionService;
import com.yyr.mapper.FamRolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_role_permission(家庭角色权限)】的数据库操作Service实现
* @createDate 2022-11-29 12:15:27
*/
@Service
public class FamRolePermissionServiceImpl extends ServiceImpl<FamRolePermissionMapper, FamRolePermission>
    implements FamRolePermissionService{
    @Autowired
    private FamRolePermissionMapper famRolePermissionMapper;

    @Override
    /**
    * @description:新增家庭角色权限
    * @Param: [form]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    public void addFamRolePermissionByFamRoleId(FamRolePermissionForm form) {
        form.getFam_permission_id().forEach(Fam_permission_id->{
            FamRolePermission famRolePermission=new FamRolePermission();
            famRolePermission.setFamRoleId(form.getFam_role_id());
            famRolePermission.setFamPermissionId(Fam_permission_id);
            famRolePermissionMapper.insert(famRolePermission);
        });
    }

    @Override
    /**
    * @description:删除该角色的家庭权限
    * @Param: [FamPermId]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    public void deleteFamRolePermissionByFamRolePermissionForm(FamRolePermissionForm form) {

        QueryWrapper<FamRolePermission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("fam_role_id",form.getFam_role_id());
        List<FamRolePermission> famRolePermissionList=famRolePermissionMapper.selectList(queryWrapper);
        Assert.notNull(famRolePermissionList,"该角色没有家庭权限!");
        if(form.getFam_permission_id()!=null){
            form.getFam_permission_id().forEach(FamPermId->{
                LambdaQueryWrapper<FamRolePermission> wrapper=new LambdaQueryWrapper<>();
                wrapper.eq(FamRolePermission::getFamRoleId,form.getFam_role_id());
                wrapper.eq(FamRolePermission::getFamPermissionId,FamPermId);
                famRolePermissionMapper.delete(wrapper);
            });
        }else {
            LambdaQueryWrapper<FamRolePermission> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(FamRolePermission::getFamRoleId,form.getFam_role_id());
            famRolePermissionMapper.delete(wrapper);
        }

    }

    @Override
    /**
    * @description:根据家庭角色id查询该角色权限
    * @Param: [FamRoleId]
    * @return: java.util.List<com.yyr.pojo.FamRolePermission>
    * @throws:
    * @author:杨亚茹
    */
    public List<FamRolePermission> queryFamRolePermissionByFamRoleId(FamRolePermissionForm form) {
        LambdaQueryWrapper<FamRolePermission> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(FamRolePermission::getFamRoleId,form.getFam_role_id());
        return this.list(queryWrapper);
    }


}




