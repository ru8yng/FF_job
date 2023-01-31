package com.yyr.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    /**
     * 创建人(user_id)
     */
    private String createdBy;
    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;
}
