package com.yyr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/20 21:46
 * @PackageName:com.yyr.dto
 * @ClassName: reportForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class reportForm {
    String userId;
    String famId;

    String title;

    String type;
    //收入 支出 预算
    List<IAEForm> iaeList;

    BigDecimal incomeFund;

    BigDecimal incomeStock;

    List<FamAssetsForm> assetsList;



}
