package finance8005.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/29 14:23
 * @PackageName:finance8005.dto
 * @ClassName: FundAndStockForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundAndStockForm {
    private String userId;

    private List<FundForm> fundFormList;

    private List<StockForm> stockFormList;
}
