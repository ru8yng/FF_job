package finance8005.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 杨亚茹
 * @Date 2023/4/16 22:41
 * @PackageName:com.yyr.dto
 * @ClassName: FundCollectForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundCollectForm {
    private String fundCode;

    private String userId;

    private String famId;
}
