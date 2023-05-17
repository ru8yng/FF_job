package bills8002.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 杨亚茹
 * @Date 2023/3/28 15:45
 * @PackageName:com.yyr.dto
 * @ClassName: IAE
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IAEForm {
    Double income;

    Double expense;

    Double budget;

    String date;

    String budgetTpye;

    String famId;

    String userId;


}
