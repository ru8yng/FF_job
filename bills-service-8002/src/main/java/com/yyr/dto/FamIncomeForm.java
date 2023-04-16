package com.yyr.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2023/3/22 16:22
 * @PackageName:com.yyr.dto
 * @ClassName: FamIncomeForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamIncomeForm {

    private String famIncomeId;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     *
     */
    private String famId;

    /**
     *
     */
    private String userId;

    /**
     *
     */
    private Date famIncomeTime;

    /**
     *
     */
    private String famIncomeAmount;

    /**
     *
     */
    private String famIncomeTypeId;

    /**
     * 备注
     */
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
