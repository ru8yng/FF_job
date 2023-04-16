package com.yyr.service;

import com.yyr.dto.FamBudgetForm;
import com.yyr.pojo.FamBudget;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_budget(家庭预算)】的数据库操作Service
* @createDate 2023-03-28 15:49:21
*/
public interface FamBudgetService extends IService<FamBudget> {

    void addFamBudget(FamBudgetForm form);

    void deleteFamBudget(String budgetId);

    void updateFamBudget(FamBudgetForm form);

    List<FamBudget> queryFamBudget(FamBudgetForm form);

}
