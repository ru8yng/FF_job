package com.yyr.service;

import com.yyr.dto.IncomeTypeForm;
import com.yyr.pojo.IncomeType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【income_type(收入类型)】的数据库操作Service
* @createDate 2023-03-04 15:00:00
*/
public interface IncomeTypeService extends IService<IncomeType> {
    void addIncomeType(IncomeTypeForm form);

    void deleteIncomeType(String incomeTypeId);

    void updateIncomeType(IncomeTypeForm form);

    List<IncomeType> queryIncomeType(IncomeTypeForm form);
}
