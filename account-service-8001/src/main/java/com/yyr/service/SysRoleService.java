package com.yyr.service;


import account8001.dto.SysRoleQueryForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyr.pojo.SysRole;

import java.util.List;

/**
* @author sheep
* @description 针对表【sys_role(用户系统角色)】的数据库操作Service
* @createDate 2022-11-29 12:16:21
*/

public interface SysRoleService extends IService<SysRole> {
    void addSysRole (SysRoleQueryForm sysRole);
    void deleteSysRole(String id);
    void updateSysRole(SysRole sysRole);
    List<SysRoleQueryForm> querySysRole(SysRoleQueryForm sysRole);
}
