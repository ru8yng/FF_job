package log8003.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2023/2/22 15:12
 * @PackageName:com.yyr.dto
 * @ClassName: LoginLogForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class LoginLogForm {
    @ApiModelProperty("开始日期 格式 yyyy-mm-dd hh24:mi:ss 例如 2022-04-25 00:00:00")
    private String startTime;
    @ApiModelProperty("结束日期 格式 yyyy-mm-dd hh24:mi:ss 例如 2022-04-25 00:00:00")
    private String endTime;
    @ApiModelProperty("分页参数 当前页")
    private Integer page;
    @ApiModelProperty("分页参数 每页数量")
    private Integer size;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("用户登录id")
    private String login_log_Id;

    @ApiModelProperty("ip地址")
    private String ipAddr;

    private Date loginTime;
}
