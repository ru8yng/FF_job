package com.yyr.service.impl;

import com.yyr.pojo.User;
import com.yyr.service.AuthService;
import com.yyr.service.UserService;
import com.yyr.service.bp.UserBp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author 杨亚茹
 * @Date 2023/2/26 10:58
 * @PackageName:com.yyr.service.impl
 * @ClassName: AuthServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserBp userBp;

    final String systemId = "ff_job";

    @Autowired
    private UserService userService;

    /**
    * @description: redis<key,user> key为ff_job+token+login
    * @Param: [token]
    * @return: com.yyr.pojo.User
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public User getUserByToken(String token) {
        String key = systemId + token + "login";
        User user = (User) redisTemplate.opsForValue().get(key);
        //是否要更新权限信息
       // Assert.isTrue(user == null, "用户信息已过期！请重新登录！");

        return user;
    }

    /**
    * @description: redis<key,token> key为ff_job+userid token为uuid
    * @Param: [user]
    * @return: java.lang.String
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public String getToken(User user) {
        //如果redis有，直接返回redis数据
        String uid = (String) redisTemplate.opsForValue().get(systemId + user.getUserId());
        if (StringUtils.isEmpty(uid)) {
            String uuid = UUID.randomUUID().toString();
            uid = uuid;
            //更新权限
            User user1=userBp.addUserPermission(user);
            System.out.println(user1);

            redisTemplate.opsForValue().set(systemId + user.getUserId(), uid, 12, TimeUnit.HOURS);  //token有效期 12小时
            //用户信息放入redis 拦截器要用到
            redisTemplate.opsForValue().set(systemId + uid + "login", user1, 12, TimeUnit.HOURS); //token有效期 12小时
        }
        return uid;
    }

    @Override
    public void logout(String token) {
        try{
            User user = (User) redisTemplate.opsForValue().get(systemId + token + "login");
            //删除user
            redisTemplate.delete(systemId + token + "login");
            //删除userid
            redisTemplate.delete(systemId + user.getUserId());
        }catch (Exception e){
        }

    }
}
