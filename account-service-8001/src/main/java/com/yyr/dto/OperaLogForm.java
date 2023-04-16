package com.yyr.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2023/2/22 16:07
 * @PackageName:com.yyr.dto
 * @ClassName: OperaLogForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@ApiModel
public class OperaLogForm {
    @ApiParam(value = "开始时间")
    private String startTime;
    @ApiParam(value = "结束时间")
    private String endTime;
    @ApiParam(value = "分页参数 当前页")
    private Integer page;
    @ApiParam(value = "分页参数 每页数量")
    private Integer size;
    private String operaLogId;

    private String operaLogName;

    @ApiParam(value = "访问接口")
    private String operaLog;

    @ApiParam("IP地址")
    private String ipaddr;

    @ApiParam("操作角色id")
    private String operaLogSysroleId;

    @ApiParam("创建人user_id")
    private String operaLogOperaby;

    private Date operaTime;
}
