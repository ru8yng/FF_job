package account8001.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/1/30 15:46
 * @PackageName:com.yyr.dto
 * @ClassName: FamPermQueryForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamPermQueryForm {
    private String famPermissionId;

    /**
     * 权限名
     */
    private String famPermissionName;
    private String famPermissionParentid;

    private String famPermissionPath;

    private String icon;

    private List<FamPermQueryForm> child = new ArrayList<>();

    /**
     * 创建人(user_id)
     */
    private String createdBy;
    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;
    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;
}
