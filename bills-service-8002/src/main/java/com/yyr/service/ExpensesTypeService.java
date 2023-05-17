package com.yyr.service;


import bills8002.dto.ExpenseTypeForm;
import com.yyr.pojo.ExpensesType;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author sheep
* @description 针对表【expenses_type(支出类型)】的数据库操作Service
* @createDate 2023-03-04 14:58:41
*/
public interface ExpensesTypeService extends IService<ExpensesType> {

    void addExpensesType(ExpenseTypeForm form);

    void deleteExpensesType(String expenseTypeId);

    void updateExpensesType(ExpenseTypeForm form);

    List<ExpensesType> queryExpensesTypes(ExpenseTypeForm form);
}
