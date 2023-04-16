package com.yyr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.dto.FamBudgetForm;
import com.yyr.pojo.FamBudget;
import com.yyr.service.FamBudgetService;
import com.yyr.mapper.FamBudgetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_budget(家庭预算)】的数据库操作Service实现
* @createDate 2023-03-28 15:49:21
*/
@Service
public class FamBudgetServiceImpl extends ServiceImpl<FamBudgetMapper, FamBudget>
    implements FamBudgetService{


    @Autowired
    private FamBudgetMapper famBudgetMapper;

    @Override
    public void addFamBudget(FamBudgetForm form) {

    }

    @Override
    public void deleteFamBudget(String budgetId) {
        FamBudget famBudget=famBudgetMapper.selectById(budgetId);
        Assert.notNull(famBudget,"该预算不存在");
        this.removeById(budgetId);
    }

    @Override
    public void updateFamBudget(FamBudgetForm form) {

    }

    @Override
    public List<FamBudget> queryFamBudget(FamBudgetForm form) {
        return null;
    }
}




