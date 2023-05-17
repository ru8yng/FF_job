package equity8004.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2023/3/24 9:42
 * @PackageName:com.yyr.dto
 * @ClassName: FamAssetsForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamAssetsForm {
    private String famAssetsId;

    /**
     * 创建人
     */
    private String createdBy;



    /**
     * 更新人
     */
    private String updatedBy;

    /**
     *
     */
    private String assetsName;


    /**
     *
     */
    private String assetsLocation;

    /**
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assetsBuytime;

    /**
     *
     */
    private String famId;

    /**
     * 资产分期(0为不分期1为分期)
     */
    private String assetsInstalment;

    /**
     * 剩余期限/月
     */
    private String instalmentSurplus;

    /**
     * 每期还款金额
     */
    private String instalmentPrice;


    /**
     *
     */
    private String remark;

    @ApiModelProperty("分页参数 当前页")
    private Integer page =1;


    @ApiModelProperty("分页参数 每页数量")
    private Integer size = 20;

    @ApiModelProperty("开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
