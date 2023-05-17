package com.yyr.service;


import account8001.dto.FamPermQueryForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyr.pojo.FamPermission;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_permission(家庭权限)】的数据库操作Service
* @createDate 2022-11-29 12:15:20
*/
public interface FamPermissionService extends IService<FamPermission> {
    void addFamPermission(FamPermQueryForm famPermission);
    void deleteFamPermission(String FamPermId);
    void updateFamPermission(FamPermQueryForm famPermission);
    List<FamPermission> queryFamPermissionList(FamPermQueryForm form);
}
