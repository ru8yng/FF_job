package com.yyr.controller;

import account8001.dto.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.netflix.discovery.converters.Auto;
import com.yyr.config.IgnoreSecurity;
import com.yyr.mapper.UserMapper;
import com.yyr.pojo.*;
import com.yyr.service.*;
import com.yyr.service.bp.UserBp;
import io.swagger.annotations.Api;
import log8003.dto.LoginLogForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import utils.CommonResponse;
import utils.IPUtil;
import utils.MD5Util;


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
    private FamilyRoleService roleService;


    @Autowired
    private FamRolePermissionService famRolePermissionService;

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
            return CommonResponse.error("密码错误！");
        }
        if(user.getStatus().equals("0")){
            return CommonResponse.error("该用户账号未激活！");
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
            AuthController.log.info("登录失败@" + e.getMessage());
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
        if(form==null){return CommonResponse.error( "注册的用户信息不为空！");}
        UserQueryForm form2=new UserQueryForm();
        form2.setEmail(form.getEmail());
        List<UserQueryForm> forms = userService.queryUserListByFrom(form2);
        if(forms.size()!=0){return CommonResponse.error( "该邮箱已被注册！");}
        if(form.getFamCheak().equals("1")){
            //创建用户及家庭
            form.setStatus("1");
            FamQueryForm family=new FamQueryForm();
            family.setStatus("1");
            family.setFamilyDesc(form.getFamDesc());
            family.setFamilyName(form.getFamName());
            familyService.addFamily(family);
            Family family1 = familyService.queryList(family).get(0);
            form.setFamilyId(family1.getFamilyId());

            //赋予用户角色及权限
            //创建角色
            FamRoleQueryForm roleQueryForm=new FamRoleQueryForm();
            roleQueryForm.setFamRoleId("1658843433679523841");
            FamRoleQueryForm init=roleService.queryFamRoleList(roleQueryForm).get(0);
            BeanUtils.copyProperties(init,roleQueryForm);
            roleQueryForm.setFamRoleId(null);
            roleQueryForm.setFamId(family1.getFamilyId());
            roleService.addFamRole(roleQueryForm);

            FamRoleQueryForm roleQueryForm1 = roleService.queryFamRoleList(roleQueryForm).get(0);
            //关联用户
            form.setFamRoleId(roleQueryForm1.getFamRoleId());
            userService.addUser(form);
//            //角色关联权限
//            FamRolePermissionForm rolePermissionForm=new FamRolePermissionForm();
//            rolePermissionForm.setFam_role_id("1658843433679523841");
//            List<String> initRolePermis=famRolePermissionService.queryFamRolePermissionByFamRoleId(rolePermissionForm).stream().map(FamRolePermission::getFamPermissionId).collect(Collectors.toList());
//            FamRolePermissionForm r=new FamRolePermissionForm();
//            r.setFam_role_id(roleQueryForm1.getFamRoleId());
//            r.setFam_permission_id(initRolePermis);
//            famRolePermissionService.addFamRolePermissionByFamRoleId(r);


        }
        if(form.getFamCheak().equals("0")){
            FamRoleQueryForm roleQueryForm=new FamRoleQueryForm();
            FamPermission init=roleService.queryFamPermissionByFamRoleId("1658843979035512833",true).get(0);
            BeanUtils.copyProperties(init,roleQueryForm);
            roleQueryForm.setFamId(form.getFamilyId());
            roleService.addFamRole(roleQueryForm);
            FamRoleQueryForm roleQueryForm1 = roleService.queryFamRoleList(roleQueryForm).get(0);
            //关联用户
            form.setFamRoleId(roleQueryForm1.getFamRoleId());
            userService.addUser(form);
            //角色关联权限
//            FamRolePermissionForm rolePermissionForm=new FamRolePermissionForm();
//            rolePermissionForm.setFam_role_id("1658843979035512833");
//            List<String> initRolePermis=famRolePermissionService.queryFamRolePermissionByFamRoleId(rolePermissionForm).stream().map(FamRolePermission::getFamPermissionId).collect(Collectors.toList());
//            FamRolePermissionForm r=new FamRolePermissionForm();
//            r.setFam_role_id(roleQueryForm1.getFamRoleId());
//            r.setFam_permission_id(initRolePermis);
//            famRolePermissionService.addFamRolePermissionByFamRoleId(r);


        }

        return CommonResponse.ok("注册成功！");
    }
}
