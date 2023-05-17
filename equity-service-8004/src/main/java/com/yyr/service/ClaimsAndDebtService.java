package com.yyr.service;


import com.yyr.pojo.ClaimsAndDebt;
import com.baomidou.mybatisplus.extension.service.IService;
import equity8004.dto.ClaimsAndDebtForm;


import java.util.List;


/**
* @author sheep
* @description 针对表【claims_and_debt(借贷)】的数据库操作Service
* @createDate 2023-03-04 15:03:23
*/
public interface ClaimsAndDebtService extends IService<ClaimsAndDebt> {
    void addCAD(ClaimsAndDebtForm form);

    void deleteCAD(String cadId);

    void updateCAD(ClaimsAndDebtForm form);

    List<ClaimsAndDebt> queryCAD(ClaimsAndDebtForm form);
}
