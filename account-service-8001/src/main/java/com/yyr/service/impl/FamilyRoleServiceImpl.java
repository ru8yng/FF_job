package com.yyr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.FamQueryForm;
import com.yyr.dto.FamRolePermissionForm;
import com.yyr.dto.FamRolePermissionUpdateDto;
import com.yyr.dto.FamRoleQueryForm;
import com.yyr.enums.FamPermissionIconEnum;
import com.yyr.mapper.FamRolePermissionMapper;
import com.yyr.pojo.FamPermission;
import com.yyr.pojo.FamRolePermission;
import com.yyr.pojo.FamilyRole;
import com.yyr.service.FamPermissionService;
import com.yyr.service.FamRolePermissionService;
import com.yyr.service.FamilyRoleService;
import com.yyr.mapper.FamilyRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private FamRolePermissionMapper famRolePermissionMapper;

    @Autowired
    private FamPermissionService famPermissionService;

    /**
    * @description:新增家庭角色
    * @Param: [familyRole]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void addFamRole(FamilyRole familyRole) {
       // System.out.println("+++++++++++++++++++++++++role"+familyRole);
        Assert.isTrue(familyRole.getFamId()!=null && familyRole.getFamRoleName()!=null,"家庭id且角色名不为空");
        List<FamPermission> list=familyRole.getPermissionList();
        familyRoleMapper.insert(familyRole);
        FamRoleQueryForm form=new FamRoleQueryForm();
        form.setFamId(familyRole.getFamId());
        form.setFamRoleName(familyRole.getFamRoleName());
        String roleId=this.queryFamRoleList(form).get(0).getFamRoleId();
        if (!list.isEmpty()){
            list.forEach(famPermission -> {
                FamRolePermission famRolePermission=new FamRolePermission();
                famRolePermission.setFamRoleId(roleId);
                famRolePermission.setFamPermissionId(famPermission.getFamPermissionId());
                famRolePermissionMapper.insert(famRolePermission);
                if(!famPermission.getChild().isEmpty()){
                    //子菜单
                    famPermission.getChild().forEach(child->{
                        FamRolePermission famRolePermission1=new FamRolePermission();
                        famRolePermission1.setFamRoleId(roleId);
                        famRolePermission1.setFamPermissionId(child.getFamPermissionId());
                        famRolePermissionMapper.insert(famRolePermission1);
                    });
                }
            });
        }


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
        FamRolePermissionForm form=new FamRolePermissionForm();
        form.setFam_role_id(id);
        famRolePermissionService.deleteFamRolePermissionByFamRolePermissionForm(form);
    }

    /**
    * @description:更新家庭角色
    * @Param: [familyRole]
    * @return: com.yyr.pojo.FamilyRole
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void updateFamRole(FamRolePermissionUpdateDto dto) {

        FamilyRole familyRole1=familyRoleMapper.selectById(dto.getRoleId());
        Assert.notNull(familyRole1,"该家庭角色不存在");

        if(dto.getFamRoleDesc()!= null && dto.getFamRoleName()!=null){
            LambdaUpdateWrapper<FamilyRole> updateWrapper= new UpdateWrapper<FamilyRole>().lambda();

            updateWrapper.eq(FamilyRole::getFamRoleId,dto.getRoleId());

            if(dto.getFamRoleDesc()!= null && dto.getFamRoleDesc().length()!=0){
                updateWrapper.set(FamilyRole::getFamRoleDesc,dto.getFamRoleDesc());
            }
            if(dto.getFamRoleName()!=null && dto.getFamRoleName().length()!=0){
                updateWrapper.set(FamilyRole::getFamRoleName,dto.getFamRoleName());
            }


            this.update(updateWrapper);
        }

        if(!dto.getPermissionList().isEmpty()){
            famRolePermissionService.updateByRoleId(dto);
        }



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
        List<FamilyRole> list=this.list(queryWrapper);
        list.forEach(role->{
            role.setPermissionList(this.queryFamPermissionByFamRoleId(role.getFamRoleId(),true));
        });
        return list;
    }

    @Override
    public List<FamPermission> queryFamPermissionByFamRoleId(String roleId, boolean isFilter) {
        List<FamPermission> permissionList = famPermissionService.list();

        List<String> permissIdList = famRolePermissionMapper.selectPermissionIdByRoleId(roleId);
        //System.out.println("+++++++++++++++++++++++++=roleperm"+permissIdList);
        permissionList.forEach(permission -> {
            permission.setCheck("0");
            if (permissIdList.contains(permission.getFamPermissionId()) && roleId != null && !roleId.isEmpty()) {
                permission.setCheck("1");
            }
        });
        if (isFilter) {
            permissionList = permissionList.stream().filter(e -> e.getCheck().equals("1")).distinct().collect(Collectors.toList());
        }


//        Collections.sort(permissionList, new Comparator<FamPermission>() {
//            @Override
//            public int compare(FamPermission o1, FamPermission o2) {
//                return Integer.valueOf(o1.getFamPermissionId()) - Integer.valueOf(o2.getFamPermissionParentid());
//            }
//        });

        //设置icon
//        for(FamPermission permission:permissionList){
//            FamPermissionIconEnum iconEnum= FamPermissionIconEnum.getTypeByCode(permission.getFamPermissionName());
//            permission.setIcon(iconEnum.getIcon());
//
//        }
        //建立权限树状图关系
        List<FamPermission> tree=buildTree(permissionList);



        return tree;
    }

    @Override
    public List<FamPermission> buildTree(List<FamPermission> permissionSet) {
        //首先查询父级id为0的权限
        List<FamPermission> list = new ArrayList<>();
        permissionSet.forEach(famPermission -> {
            //System.out.println("name"+famPermission.getFamPermissionName()+"parentlength"+famPermission.getFamPermissionParentid().length());
            //一级菜单
            if (famPermission.getFamPermissionParentid().length()==0 || famPermission.getFamPermissionParentid()==null ) {
                list.add(famPermission);
            }
        });

        //System.out.println(list);



        for (FamPermission permission : list) {
            //设置子菜单
            permission.setChild(getChildren(permission, permissionSet));
        }

        //每个子菜单排序
//        list.sort(new Comparator<FamPermission>() {
//            @Override
//            public int compare(FamPermission o1, FamPermission o2) {
//                return Integer.valueOf(o1.getFamPermissionId()) - Integer.valueOf(o2.getFamPermissionParentid());
//            }
//        });
        permissionSet = null;
        return list;
    }

    public List<FamPermission> getChildren(FamPermission permissionVo, List<FamPermission> permissionSet) {

        List<FamPermission> childList = permissionVo.getChild();
        //获取全部子菜单
        for (FamPermission permissionVo1 : permissionSet) {
            if (Objects.equals(permissionVo1.getFamPermissionParentid(), permissionVo.getFamPermissionId())) {
                //添加进子菜单
                childList.add(permissionVo1);
            }
        }
        for (FamPermission permissionVo1 : childList) {
            permissionVo1.setChild(getChildren(permissionVo1, permissionSet));
        }
        if (childList.isEmpty()) {
            return childList;
        }

        //子菜单排序
//        childList.sort(new Comparator<FamPermission>() {
//            @Override
//            public int compare(FamPermission o1, FamPermission o2) {
//                return Integer.valueOf(o1.getFamPermissionId()) - Integer.valueOf(o2.getFamPermissionParentid());
//            }
//        });
        return childList;
    }

}




