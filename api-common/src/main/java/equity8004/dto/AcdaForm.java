package equity8004.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/24 17:37
 * @PackageName:com.yyr.dto
 * @ClassName: BillsForm
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcdaForm {

    String date;

    String famId;

    String userId;
    List<ClaimsAndDebtForm> cads;

    List<FamAssetsForm> assets;
}
