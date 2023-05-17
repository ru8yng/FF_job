package account8001.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/8 16:54
 * @PackageName:com.yyr.dto
 * @ClassName: FamRolePermissionUpdateDto
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamRolePermissionUpdateDto {
    private String roleId;
    private String famRoleDesc;

    private String famRoleName;

    private List<FamPermQueryForm> permissionList;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;
}
