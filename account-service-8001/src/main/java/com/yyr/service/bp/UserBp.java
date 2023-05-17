package com.yyr.service.bp;

import account8001.dto.FamRoleQueryForm;
import com.yyr.pojo.FamilyRole;
import com.yyr.pojo.User;
import com.yyr.service.FamilyRoleService;
import com.yyr.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/2/26 11:50
 * @PackageName:com.yyr.service.bp
 * @ClassName: UserBp
 * @Description: TODO
 * @Version 1.0
 */

@Component
public class UserBp {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private FamilyRoleService familyRoleService;

    public User addUserPermission(User user) {
        List<FamRoleQueryForm> famRoleList = familyRoleService.queryFamRoleList(new FamRoleQueryForm());
        famRoleList.forEach(role -> {
            if (role.getFamRoleId().equals(user.getFamRoleId())) {
                user.setFamRoleId(role.getFamRoleId());
            }
        });
        if (user.getFamRoleId() != null && !user.getFamRoleId().isEmpty()) {

            user.setPermissionList(familyRoleService.queryFamPermissionByFamRoleId(user.getFamRoleId(), true));
        }
        return user;

    }

}
