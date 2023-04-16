package com.yyr.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2023/3/21 10:20
 * @PackageName:com.yyr.dto
 * @ClassName: FamExpenseForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamExpenseForm {
    private String famExpenseId;

    private String createdBy;

    private String updatedBy;

    private String userId;

    private Date expenseTime;

    private String famId;

    private String famExpenseAmount;

    private String famExpenseTypeId;

    private String remark;

    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;
}
