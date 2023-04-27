package com.yyr.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.yyr.dto.FamBudgetForm;
import com.yyr.dto.FamExpenseForm;
import com.yyr.dto.FamIncomeForm;
import com.yyr.dto.IAEForm;
import com.yyr.pojo.FamBudget;
import com.yyr.pojo.FamExpense;
import com.yyr.pojo.FamIncome;
import com.yyr.service.FamBudgetService;
import com.yyr.service.FamExpenseService;
import com.yyr.service.FamIncomeService;
import com.yyr.service.IAEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author 杨亚茹
 * @Date 2023/3/28 15:56
 * @PackageName:com.yyr.service.impl
 * @ClassName: IAEServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class IAEServiceImpl implements IAEService {
    @Autowired
    private FamExpenseService famExpenseService;

    @Autowired
    private FamIncomeService famIncomeService;

    @Autowired
    private FamBudgetService famBudgetService;



    @Override
    public IAEForm queryIaeCurrent(IAEForm iae) {
        FamIncomeForm incomeForm=new FamIncomeForm();
        FamExpenseForm expenseForm=new FamExpenseForm();
        FamBudgetForm budgetForm=new FamBudgetForm();
        Date begin=new Date();
        Date end=new Date();
        switch (iae.getDate()){
            case "month":
                begin = DateUtil.beginOfMonth(new Date());
                end = DateUtil.endOfMonth(new Date());
                break;
            case "week":
                begin = DateUtil.beginOfWeek(new Date());
                end = DateUtil.beginOfWeek(new Date());
                break;
            case "year":
                begin = DateUtil.beginOfYear(new Date());
                end = DateUtil.endOfYear(new Date());
                break;
            default:
                break;
        }
        incomeForm.setStartTime(begin);
        incomeForm.setEndTime(end);
        budgetForm.setStartTime(begin);
        budgetForm.setEndTime(end);
        if (iae.getFamId()!=null){
            incomeForm.setFamId(iae.getFamId());
            expenseForm.setFamId(iae.getFamId());
            budgetForm.setFamId(iae.getFamId());
        }
        if(iae.getUserId()!=null){
            incomeForm.setUserId(iae.getUserId());
            expenseForm.setUserId(iae.getUserId());
            budgetForm.setUserId(iae.getUserId());
        }

        expenseForm.setStartTime(begin);
        expenseForm.setEndTime(end);
        List<FamExpense> expenses=famExpenseService.queryFamExpense(expenseForm);
        List<FamIncome> incomes=famIncomeService.queryFamIncome(incomeForm);
        List<FamBudget> budgets=famBudgetService.queryFamBudget(budgetForm);

        AtomicReference<Double> income= new AtomicReference<>(0.0);
        incomes.forEach(in->{
            income.updateAndGet(v -> v + Double.valueOf(in.getFamIncomeAmount()));
        });

        iae.setIncome(income.get());

        AtomicReference<Double> expen= new AtomicReference<>(0.0);
        expenses.forEach(exp->{
            expen.updateAndGet(v -> v + Double.valueOf(exp.getFamExpenseAmount()));
        });
        iae.setExpense(expen.get());
        AtomicReference<Double> budget= new AtomicReference<>(0.0);

        budgets.forEach(bgt->{
            budget.updateAndGet(v -> v + Double.valueOf(bgt.getUserBudgetAmount()));
        });

        iae.setBudget(budget.get());


        //预算

        return iae;
    }
}
