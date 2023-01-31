package com.yyr.service;

import com.yyr.dto.UserQueryForm;
import com.yyr.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-11-29 12:14:55
*/
public interface UserService extends IService<User> {

    void addUser(User user);

    void deleteUser(String id);

    void enable(String userId,String status);

    void resetPwd(String id);

    void updateUserBasicAttributes(User form);

    void changeUserFmId(String id,String fm_id);

    void changePwd(String id,String pwd);

    List<User> queryUserListByFrom(UserQueryForm form);

}
