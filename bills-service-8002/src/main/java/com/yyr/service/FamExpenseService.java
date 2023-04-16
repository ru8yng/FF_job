package com.yyr.service;

import com.yyr.dto.FamExpenseForm;
import com.yyr.pojo.FamExpense;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_expense(家庭支出)】的数据库操作Service
* @createDate 2023-03-04 14:59:12
*/
public interface FamExpenseService extends IService<FamExpense> {

    void addFamExpense(FamExpenseForm form);

    void deleteFamExpense(String famExpenseId);

    void updateFamExpense(FamExpenseForm form);

    List<FamExpense> queryFamExpense(FamExpenseForm form);

}
