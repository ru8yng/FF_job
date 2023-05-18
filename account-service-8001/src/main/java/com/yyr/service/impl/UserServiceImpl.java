package com.yyr.service.impl;

import account8001.dto.UserQueryForm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.constdata.UserConst;
import com.yyr.mapper.FamilyMapper;
import com.yyr.mapper.FamilyRoleMapper;
import com.yyr.pojo.Family;
import com.yyr.pojo.User;
import com.yyr.service.UserService;
import com.yyr.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import utils.MD5Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author sheep
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-11-29 12:14:55
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private FamilyRoleMapper familyRoleMapper;

    /**
    * @description:新增用户
    * @Param: [user]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void addUser(UserQueryForm form) {
        Assert.isTrue(form.getUserName()!=null&&form.getUserPwd()!=null,"用户名不为空且用户密码不为空");
        User user=new User();
        user.setUserPwd(MD5Util.encodeByMD5(form.getUserPwd()));
        if(form.getUserName()!=null && form.getUserName().length()!=0){
            user.setUserName(form.getUserName());
        }
        if(form.getSex()!=null && form.getSex().length()!=0){
            user.setSex(form.getSex());
        }
        if(form.getBirthday()!=null ){
            user.setBirthday(form.getBirthday());
        }
        if(form.getEmail()!=null && form.getEmail().length()!=0){
            user.setEmail(form.getEmail());
        }
        if(form.getQqnum()!=null && form.getQqnum().length()!=0){
            user.setQqnum(form.getQqnum());
        }
        if(form.getFamRoleId()!=null && form.getFamRoleId().length()!=0){
            user.setFamRoleId(form.getFamRoleId());
        }
        if(form.getFamilyId()!=null && form.getFamilyId().length()!=0){
            user.setFamilyId(form.getFamilyId());
        }
        if(form.getStatus()!=null && form.getStatus().length()!=0){
            user.setStatus(form.getStatus());
        }

        userMapper.insert(user);
    }


    /**
    * @description:删除用户
    * @Param: [id]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void deleteUser(String id) {
        User user=userMapper.selectById(id);
        Assert.notNull(user,"该用户不存在！");
        userMapper.deleteById(id);
    }

    /**
    * @description:停用/启动用户
    * @Param: [userId, isActive]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void enable(String userId,String status) {
        User user=userMapper.selectById(userId);
        Assert.notNull(user,"该用户不存在！");
        user.setStatus(status);
        userMapper.updateById(user);
    }

    /**
    * @description:重置用户密码为默认密码
    * @Param: [id]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void resetPwd(String id)throws IllegalArgumentException {
        User user= userMapper.selectById(id);
        Assert.notNull(user,"对应用户不存在！");
        user.setUserPwd(MD5Util.encodeByMD5(UserConst.USER_DEFAULT_PASSWORD));
        userMapper.updateById(user);
    }



    /**
    * @description:更新用户基本资料
    * @Param: [user]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void updateUserBasicAttributes(UserQueryForm form) throws IllegalArgumentException {
        //家庭id改变 fam_role_id置空
        //BasicAttributes
        LambdaUpdateWrapper<User> updateWrapper=new UpdateWrapper<User>().lambda();
        updateWrapper.eq(User::getUserId,form.getUserId());

        if(form.getQqnum()!=null && form.getQqnum().length()!=0){
            updateWrapper.set(User::getQqnum,form.getQqnum());
        }
        if(form.getSex()!=null && form.getSex().length()!=0){
            updateWrapper.set(User::getSex,form.getSex());
        }
        if(form.getBirthday()!=null ){
            updateWrapper.set(User::getBirthday,form.getBirthday());
        }
        if(form.getEmail()!=null && form.getEmail().length()!=0){
            updateWrapper.set(User::getEmail,form.getEmail());
        }

        if(form.getFamRoleId()!=null && form.getFamRoleId().length()!=0){
            updateWrapper.set(User::getFamRoleId,form.getFamRoleId());
        }

        if(form.getUpdatedBy()!=null && form.getUpdatedBy().length()!=0){
            updateWrapper.set(User::getUpdatedBy,form.getUpdatedBy());
        }

        this.update(updateWrapper);

    }

    /**
    * @description:修改用户家庭id
    * @Param: [id, fm_id]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void changeUserFmId(String id, String fm_id) {
        User user= userMapper.selectById(id);
        Assert.notNull(user,"对应用户不存在！");
        user.setFamilyId(fm_id);
        user.setFamRoleId("");
        userMapper.updateById(user);
    }

    /**
    * @description:修改密码
    * @Param: [id, pwd]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void changePwd(String id, String pwd) {
        User user= userMapper.selectById(id);
        Assert.notNull(user,"对应用户不存在！");
        user.setUserPwd(MD5Util.encodeByMD5(pwd));
        userMapper.updateById(user);
    }

    @Override
    /**
    * @description:查询用户列表
    * @Param: [form]
    * @return: java.util.List<com.yyr.pojo.User>
    * @throws:
    * @author:杨亚茹
    */
    public List<UserQueryForm> queryUserListByFrom(UserQueryForm form) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getUserId()!=null && form.getUserId().length()!=0){
            queryWrapper.eq(User::getUserId,form.getUserId());
        }
        if(form.getUserName()!=null && form.getUserName().length()!=0){
            queryWrapper.like(User::getUserName,form.getUserName());
        }
        if(form.getSex()!=null && form.getSex().length()!=0){
            queryWrapper.eq(User::getSex,form.getSex());
        }
        if(form.getEmail()!=null && form.getEmail().length()!=0){
            queryWrapper.eq(User::getEmail,form.getEmail());
        }
        if(form.getQqnum()!=null && form.getQqnum().length()!=0){
            queryWrapper.eq(User::getQqnum,form.getQqnum());
        }
        if(form.getFamilyId()!=null && form.getFamilyId().length()!=0){
            queryWrapper.eq(User::getFamilyId,form.getFamilyId());
        }
        if(form.getFamRoleId()!=null && form.getFamRoleId().length()!=0){
            queryWrapper.eq(User::getFamRoleId,form.getFamRoleId());
        }
        if(form.getStatus()!=null && form.getStatus().length()!=0){
            queryWrapper.eq(User::getStatus,form.getStatus());
        }
        if(form.getStartTime()!=null && form.getEndTime()!=null){
            queryWrapper.between(User::getCreatedTime,form.getStartTime(),form.getEndTime());
        }

        queryWrapper.orderByAsc(User::getUserId);

        List<UserQueryForm> forms=new ArrayList<>();
        List<User> list=this.list(queryWrapper).stream().filter(user -> !user.getFamilyId().isEmpty()).collect(Collectors.toList());
        list.forEach(user -> {
            UserQueryForm form1 = new UserQueryForm();
            user.setFamilyId(familyMapper.selectById(user.getFamilyId()).getFamilyName());
            user.setFamRoleId(familyRoleMapper.selectById(user.getFamRoleId()).getFamRoleName());
            BeanUtils.copyProperties(user, form1);
            forms.add(form1);
        });
        return forms;
    }

    @Override
    public List<UserQueryForm> queryUserListByFrom1(UserQueryForm form) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        if(form.getUserId()!=null && form.getUserId().length()!=0){
            queryWrapper.eq(User::getUserId,form.getUserId());
        }
        if(form.getUserName()!=null && form.getUserName().length()!=0){
            queryWrapper.like(User::getUserName,form.getUserName());
        }
        if(form.getSex()!=null && form.getSex().length()!=0){
            queryWrapper.eq(User::getSex,form.getSex());
        }
        if(form.getEmail()!=null && form.getEmail().length()!=0){
            queryWrapper.eq(User::getEmail,form.getEmail());
        }
        if(form.getQqnum()!=null && form.getQqnum().length()!=0){
            queryWrapper.eq(User::getQqnum,form.getQqnum());
        }
        if(form.getFamilyId()!=null && form.getFamilyId().length()!=0){
            queryWrapper.eq(User::getFamilyId,form.getFamilyId());
        }
        if(form.getFamRoleId()!=null && form.getFamRoleId().length()!=0){
            queryWrapper.eq(User::getFamRoleId,form.getFamRoleId());
        }
        if(form.getStatus()!=null && form.getStatus().length()!=0){
            queryWrapper.eq(User::getStatus,form.getStatus());
        }
        if(form.getStartTime()!=null && form.getEndTime()!=null){
            queryWrapper.between(User::getCreatedTime,form.getStartTime(),form.getEndTime());
        }

        queryWrapper.orderByAsc(User::getUserId);

        List<UserQueryForm> forms=new ArrayList<>();
        List<User> list=this.list(queryWrapper).stream().filter(user -> !user.getFamilyId().isEmpty()).collect(Collectors.toList());
        list.forEach(user -> {
            UserQueryForm form1 = new UserQueryForm();
            BeanUtils.copyProperties(user, form1);
            forms.add(form1);
        });
        return forms;
    }
}




