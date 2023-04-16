package com.yyr.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 杨亚茹
 * @Date 2023/3/27 16:28
 * @PackageName:com.yyr.dto
 * @ClassName: fundcode_search
 * @Description: TODO
 * @Version 1.0
 */
/*
* @description:http://fund.eastmoney.com/js/fundcode_search.js dto
* @Param:
* @return:
* @throws:
* @author:杨亚茹
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundcodeSearch {
    //
    private String fundcode;

    @ApiParam("简称")
    private String abbreviation;

    private String name;

    private String type;

    @ApiParam("拼音全称")
    private String fullPhoneticName;
}
