package com.yyr.service;


import account8001.dto.UserQueryForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyr.pojo.User;

import java.util.List;

/**
* @author sheep
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-11-29 12:14:55
*/
public interface UserService extends IService<User> {

    void addUser(UserQueryForm form);

    void deleteUser(String id);

    void enable(String userId,String status);

    void resetPwd(String id);

    void updateUserBasicAttributes(UserQueryForm form);

    void changeUserFmId(String id,String fm_id);

    void changePwd(String id,String pwd);

    List<UserQueryForm> queryUserListByFrom(UserQueryForm form);
    List<UserQueryForm> queryUserListByFrom1(UserQueryForm form);

}
