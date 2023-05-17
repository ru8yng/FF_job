package finance8005.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/30 10:51
 * @PackageName:finance8005.dto
 * @ClassName: sh000001
 * @Description: TODO
 * @Version 1.0
 */

@Data
public class sh {

    List<daydata> day;
    Object qt;

    Object mx_price;

    String prec;

    String version;

}
