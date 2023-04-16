package com.yyr.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 杨亚茹
 * @Date 2023/2/26 15:31
 * @PackageName:com.yyr.dto
 * @ClassName: LoginForm
 * @Description: TODO
 * @Version 1.0
 */

@ApiModel
@Data
public class LoginForm {
    @ApiModelProperty("username")
    private String username;

    @ApiModelProperty("password")
    private String password;
}
