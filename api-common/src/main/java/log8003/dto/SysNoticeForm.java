package log8003.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2023/3/4 14:44
 * @PackageName:com.yyr.dto
 * @ClassName: SysNoticeForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@ApiModel
public class SysNoticeForm {
    @ApiParam(value = "开始时间")
    private String startTime;
    @ApiParam(value = "结束时间")
    private String endTime;
    @ApiParam(value = "分页参数 当前页")
    private Integer page;
    @ApiParam(value = "分页参数 每页数量")
    private Integer size;

    private String sysNoticeId;

    private String createdBy;

    private String updatedBy;

    private String noticeTitle;

    /**
     *
     */
    private String noticeContent;

    /**
     *
     */
    private Date noticeStarttime;

    /**
     *
     */
    private Date noticeEndtime;
}
