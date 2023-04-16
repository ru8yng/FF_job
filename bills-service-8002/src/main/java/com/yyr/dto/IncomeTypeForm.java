package com.yyr.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2023/3/22 16:55
 * @PackageName:com.yyr.dto
 * @ClassName: IncomeTypeForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
public class IncomeTypeForm {
    private String incomeTypeId;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(fill= FieldFill.INSERT)
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
    private String incomeTypeName;

    /**
     *
     */
    private String incomeTypeDesc;

    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;
}
