package com.yyr.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yyr.config.IgnoreSecurity;
import com.yyr.dto.CommonResponse;
import com.yyr.dto.LoginForm;
import com.yyr.dto.LoginLogForm;
import com.yyr.dto.UserQueryForm;
import com.yyr.mapper.UserMapper;
import com.yyr.pojo.User;
import com.yyr.service.AuthService;
import com.yyr.service.CommonService8003;
import com.yyr.service.FamilyService;
import com.yyr.service.UserService;
import com.yyr.service.bp.UserBp;
import com.yyr.utils.IPUtil;
import com.yyr.utils.MD5Util;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/2/26 11:59
 * @PackageName:com.yyr.controller
 * @ClassName: AuthController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "认证接口")
@RequestMapping("/auth")
@RestController
@Slf4j(topic = "auth")
public class AuthController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserBp userBp;

    @Autowired
    private AuthService authService;

    @Autowired
    private CommonService8003 commonService8003;

    @Autowired
    private FamilyService familyService;

    @IgnoreSecurity
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @Transactional
    public CommonResponse<?> login(@RequestBody LoginForm form, HttpServletRequest request) {
        List<User> userList = userMapper.selectList(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, form.getUsername()));
        Assert.isTrue(!userList.isEmpty(), "未找到对应用户！");
        User user = userList.get(0);
        if (!user.getUserPwd().equals(MD5Util.encodeByMD5(form.getPassword()))) {
            throw new IllegalArgumentException("密码错误！");
        }
        String token = authService.getToken(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);

        //String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String ip = IPUtil.GetIpAddress(request);

        LoginLogForm logLogin=new LoginLogForm();

        logLogin.setIpAddr(ip);
        logLogin.setLoginTime(new Date());
        logLogin.setUserId(user.getUserId());
        logLogin.setUsername(user.getUserName());

        commonService8003.addSysLoginLog(logLogin);

        return CommonResponse.ok(jsonObject);
    }

    @GetMapping(value = "/getUserInfo", produces = "application/json;charset=UTF-8")
    public CommonResponse<?> getUserInfo(@RequestHeader(value ="X-Token") String token) {
        User user = null;
        User user1;
        try {
            Assert.isTrue(null != token && !token.isEmpty(), "未获取到有效的Token");
            user = authService.getUserByToken(token);
            user1 = userBp.addUserPermission(user);
            Assert.isTrue(user != null, "用户信息已过期！请重新登录！");
        } catch (Exception e) {
            log.info("登录失败@" + e.getMessage());
            return CommonResponse.error(CommonResponse.USER_NOT_LOGIN_ERROR_CODE, e.getMessage());
        }
        return CommonResponse.ok(user1);
    }

    @GetMapping(value = "/getUserTest")
    public CommonResponse<?> getUserTest() {
        List<User> userList=userService.list();
        return CommonResponse.ok(userList);
    }

    /**
     * 描述  根据token获取用户信息
     *
     * @param token 获取用户信息
     * @return {@link null  }
     * @throws
     * @author
     */
    @IgnoreSecurity
    @PostMapping(value = "/logout", produces = "application/json;charset=UTF-8")
    public CommonResponse<?> logout(@RequestHeader("X-Token") String token) {
        authService.logout(token);
        return CommonResponse.ok("退出登录成功！");

    }
    @IgnoreSecurity
    @PostMapping(value = "/register", produces = "application/json;charset=UTF-8")
    @Transactional
    public CommonResponse<?> register(@RequestBody UserQueryForm form){
        Assert.isTrue(form!=null, "注册的用户信息不为空！");
        if(form.getFamCheak().equals("1")){
            form.setStatus("1");

            userService.addUser(form);
            UserQueryForm form1=new UserQueryForm();
            form1.setUserName(form.getUserName());
            form1.setUserPwd(MD5Util.encodeByMD5(form.getUserPwd()));
            User user=userService.queryUserListByFrom(form1).get(0);
            form.getFamily().setCreatedBy(user.getUserId());
            familyService.addFamily(form.getFamily());
        }
        if(form.getFamCheak().equals("0")){
            userService.addUser(form);
        }

        return CommonResponse.ok("注册成功！");
    }
}
