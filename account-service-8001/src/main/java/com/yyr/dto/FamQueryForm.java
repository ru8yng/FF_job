package com.yyr.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 杨亚茹
 * @Date 2022/12/1 18:20
 * @PackageName:com.yyr.dto
 * @ClassName: FmQueryFrom
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamQueryForm {

    private String familyId;

    /**
     *
     */
    private String familyName;

    private String createdBy;

    private String status;
    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;
}
