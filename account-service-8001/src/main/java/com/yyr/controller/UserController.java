package com.yyr.controller;

import account8001.dto.StatusChangeDto;
import account8001.dto.UserQueryForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyr.config.logCustom;
import com.yyr.pojo.User;
import com.yyr.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import utils.CommonResponse;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2022/11/29 17:12
 * @PackageName:com.yyr.controller
 * @ClassName: UserController
 * @Description: TODO
 * @Version 1.0
 */

@Api(tags = "用户接口")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("/新增用户")
    @logCustom(description = "新增用户")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addUser")
    public CommonResponse<?> addUser(@RequestBody UserQueryForm user){
        Assert.notNull(user,"新增的用户不能为空");
        userService.addUser(user);
        return CommonResponse.ok("新增用户成功！");
    }

    @ApiOperation("删除用户")
    @logCustom(description = "删除用户")
    @GetMapping("/deleteUser/{id}")
    public  CommonResponse<?> deleteUser(@PathVariable("id") String id){
        Assert.notNull(id,"用户id不能为空！");
        userService.deleteUser(id);
        return CommonResponse.ok("删除成功！");
    }

    @ApiOperation("停用/启动用户")
    @logCustom(description = "停用/启动用户")
    @PostMapping("/enable")
    public CommonResponse<?> enableUser(@RequestBody StatusChangeDto form){
        Assert.isTrue(form!=null && form.getStatus()!=null && form.getUserId()!=null,"id不为空且账户状态不为空");
        userService.enable(form.getUserId(), form.getStatus());
        return CommonResponse.ok("用户状态修改成功！");
    }

    @ApiOperation("重置用户密码")
    @logCustom(description = "重置用户密码")
    @GetMapping("/resetUserPwd/{userId}")
    public CommonResponse<?> resetUserPwd(@PathVariable  String userId){
        Assert.notNull(userId,"用户id不为空！");
        userService.resetPwd(userId);
        return CommonResponse.ok("重置密码成功！");
    }

    @ApiOperation("更新用户")
    @PostMapping("/updateUserBasicAttributes")
    public CommonResponse<?> updateUserBasicAttributes(@RequestBody UserQueryForm form){
        Assert.notNull(form,"更新用户不为空！");
        userService.updateUserBasicAttributes(form);
        return CommonResponse.ok("更新用户成功！");
    }

    @ApiOperation("修改用户密码")
    @logCustom(description = "修改用户密码")
    @PostMapping("/changeUserPwd")
    public CommonResponse<?> changeUserPwd(String id,String pwd){
        Assert.isTrue(id!=null && pwd!=null,"用户id不为空且修改密码不为空");
        userService.changePwd(id,pwd);
        return CommonResponse.ok("修改用户密码成功！");
    }

    @ApiOperation("修改用户家庭id")
    @logCustom(description = "修改用户家庭id")
    @PostMapping("/changeUserFmId")
    public CommonResponse<?> changeUserFmId(String userId,String fm_id){
        Assert.isTrue(userId!=null && fm_id!=null,"用户id不为空且家庭id不为空");
        userService.changeUserFmId(userId,fm_id);
        return CommonResponse.ok("修改用户家庭id成功！");
    }


    @ApiOperation("根据UserQueryFrom查询用户列表")
    @PostMapping("/queryUserListPage")
    public CommonResponse<?> queryUserListPage(@RequestBody UserQueryForm form){
        Assert.isTrue(form!=null,"UserQueryForm不为空");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<UserQueryForm> userList= userService.queryUserListByFrom(form);
        return CommonResponse.ok(new PageInfo<>(userList));
    }

    @ApiOperation("根据UserQueryFrom查询用户列表")
    @PostMapping("/queryUserList")
    public CommonResponse<?> queryUserList(@RequestBody UserQueryForm form){
        Assert.isTrue(form!=null,"UserQueryForm不为空");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<UserQueryForm> userList= userService.queryUserListByFrom(form);
        return CommonResponse.ok(userList);
    }

    @ApiOperation("根据UserQueryFrom查询用户列表")
    @PostMapping("/queryUserList1")
    public CommonResponse<?> queryUserList1(@RequestBody UserQueryForm form){
        Assert.isTrue(form!=null,"UserQueryForm不为空");
        if (null != form && null != form.getPage() && null != form.getSize()) {
            PageHelper.startPage(form.getPage(), form.getSize());
        }
        List<UserQueryForm> userList= userService.queryUserListByFrom1(form);
        return CommonResponse.ok(userList);
    }

}
