package com.yyr.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 杨亚茹
 * @Date 2023/1/31 16:32
 * @PackageName:com.yyr.dto
 * @ClassName: SysRoleQueryForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleQueryForm {

    private String sysRoleId;

    /**
     *
     */
    private String sysRoleName;

    /**
     *
     */
    private String sysRoleDesc;

    /**
     * 创建人(user_id)
     */
    private String createdBy;
    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;
}
