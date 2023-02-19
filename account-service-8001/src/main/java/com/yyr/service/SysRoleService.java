package com.yyr.service;

import com.yyr.dto.SysRoleQueryForm;
import com.yyr.pojo.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【sys_role(用户系统角色)】的数据库操作Service
* @createDate 2022-11-29 12:16:21
*/
public interface SysRoleService extends IService<SysRole> {
    void addSysRole (SysRole sysRole);
    void deleteSysRole(String id);
    void updateSysRole(SysRole sysRole);
    List<SysRole> querySysRole(SysRoleQueryForm sysRole);
}
