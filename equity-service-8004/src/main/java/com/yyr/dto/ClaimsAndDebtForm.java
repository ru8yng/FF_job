package com.yyr.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2023/3/24 9:42
 * @PackageName:com.yyr.dto
 * @ClassName: ClaimsAndDebtForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
public class ClaimsAndDebtForm {

    private String cadId;

    /**
     * 创建人
     */
    private String createdBy;



    /**
     * 更新人
     */
    private String updatedBy;



    /**
     * 0借入1借出
     */
    private String cadType;

    /**
     * 债权人
     */
    private String creditor;

    /**
     * 债务人
     */
    private String obligor;

    /**
     *
     */
    private String creditorTel;

    /**
     *
     */
    private String obligorTel;

    /**
     *
     */
    private String cadAmount;

    /**
     * 借入/借出时间
     */
    private Date cadTime;

    /**
     * 偿还时间
     */
    private Date cadRepaymentTime;

    /**
     * 预计偿还时间
     */
    private Date cadPlanRepaymentTime;

    /**
     *
     */
    private String remark;

    /**
     * 0已完成1仍存在
     */
    private String cadStatus;

    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

}
