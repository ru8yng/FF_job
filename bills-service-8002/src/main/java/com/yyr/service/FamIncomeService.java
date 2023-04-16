package com.yyr.service;

import com.yyr.dto.FamIncomeForm;
import com.yyr.pojo.FamIncome;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_income(家庭收入)】的数据库操作Service
* @createDate 2023-03-04 14:59:35
*/
public interface FamIncomeService extends IService<FamIncome> {
    void addFamIncome(FamIncomeForm form);

    void deleteFamIncom(String famIncomeId);

    void updateFamIncome(FamIncomeForm form);

    List<FamIncome> queryFamIncome(FamIncomeForm form);
}
