package com.yyr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 杨亚茹
 * @Date 2023/4/11 21:40
 * @PackageName:com.yyr.dto
 * @ClassName: StatusChangeDto
 * @Description: TODO
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusChangeDto {
    String id;
    String status;
}
