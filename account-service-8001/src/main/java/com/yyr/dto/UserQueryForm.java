package com.yyr.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 杨亚茹
 * @Date 2022/12/1 12:36
 * @PackageName:com.yyr.dto
 * @ClassName: UserQueryFrom
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryForm {
    private String userId;
    private String userName;
    private String sex;
    private String email;
    private String qqnum;
    private String familyId;
    private String famRoleId;
    private String status;
    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;
}
