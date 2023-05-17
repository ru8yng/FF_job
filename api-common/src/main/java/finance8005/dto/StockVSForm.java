package finance8005.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 杨亚茹
 * @Date 2023/4/16 17:24
 * @PackageName:com.yyr.dto
 * @ClassName: StockVSForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockVSForm {

    @ApiParam("sh or hk or us or sz")
    private String type;

    private String stockCode;

    private String userId;

    private String famId;
}
