package equity8004.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
@NoArgsConstructor
public class ClaimsAndDebtForm {

    private String cadId;

    /**
     * 创建人
     */
    @ApiParam("userId")
    private String createdBy;

    private String famId;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cadTime;

    /**
     * 偿还时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cadRepaymentTime;

    /**
     * 预计偿还时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
