package account8001.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/1/30 17:55
 * @PackageName:com.yyr.dto
 * @ClassName: FamRoleQueryForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamRoleQueryForm {
    private String famRoleId;

    /**
     *
     */
    private String famRoleName;

    /**
     *
     */
    private String famRoleDesc;

    /**
     *
     */
    private String famId;

    /**
     * 创建人(user_id)
     */
    private String createdBy;

    private List<FamPermQueryForm> permissionList;

    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;
}
