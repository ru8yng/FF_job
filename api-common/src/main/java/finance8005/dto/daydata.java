package finance8005.dto;

import lombok.Data;

/**
 * @Author 杨亚茹
 * @Date 2023/4/30 10:54
 * @PackageName:finance8005.dto
 * @ClassName: day
 * @Description: TODO
 * @Version 1.0
 */

@Data
public class daydata {

    //交易日，开盘价，收盘价，最高价，最低价,总手
    String date;

    String open;

    String close;

    String lowest;

    String highest;

    String zs;
}
