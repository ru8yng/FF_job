package finance8005.dto;

import lombok.Data;

import java.util.HashMap;

/**
 * @Title : PlatFormOutputDto
 * @Package :com.scjydz.dto
 * @Description :TODO（处理数据返回值）
 * @Author: 杨亚茹
 * @Date: 创建时间 2022-08-10
 */
@Data
public class PlatFormOutputDto {

    private Long code;

    private String msg;

    private HashMap<String,Object> data;


}
