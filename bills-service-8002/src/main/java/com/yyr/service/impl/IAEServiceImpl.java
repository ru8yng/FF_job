package com.yyr.service.impl;

import bills8002.dto.FamBudgetForm;
import bills8002.dto.FamExpenseForm;
import bills8002.dto.FamIncomeForm;
import bills8002.dto.IAEForm;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateUtil;
import com.yyr.pojo.FamBudget;
import com.yyr.pojo.FamExpense;
import com.yyr.pojo.FamIncome;
import com.yyr.service.FamBudgetService;
import com.yyr.service.FamExpenseService;
import com.yyr.service.FamIncomeService;
import com.yyr.service.IAEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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

    @Override
    public List<IAEForm> getExpenseBudgentLine(IAEForm iaeForm) {

        Date begin = DateUtil.beginOfMonth(new Date());
        Date end = DateUtil.endOfMonth(new Date());
        //获取时间范围
        DateRange dateRange = DateUtil.range( begin,  end, DateField.DAY_OF_MONTH);
        //获取当前月预算
        FamBudgetForm budgetForm=new FamBudgetForm();
        budgetForm.setStartTime(begin);
        budgetForm.setEndTime(end);
        budgetForm.setUserId(iaeForm.getUserId());
        List<FamBudget> budgets=famBudgetService.queryFamBudget(budgetForm);
        double v1 = budgets.size() == 0 ? 0.0 : Double.valueOf(budgets.get(0).getUserBudgetAmount());
        AtomicReference<Double> bufgentMonth=new AtomicReference<>(v1);

        List<IAEForm> iaeForms=new ArrayList<>();
        IAEForm iae0=new IAEForm();
        iae0.setExpense(0.0);
        iae0.setBudget(bufgentMonth.get());
        iae0.setDate("0");
        iaeForms.add(iae0);

        dateRange.forEach(date->{
            //每天消费
            Date day_begin = DateUtil.beginOfDay(date);
            Date day_end = DateUtil.endOfDay(date);
            FamExpenseForm expenseForm=new FamExpenseForm();
            expenseForm.setStartTime(day_begin);
            expenseForm.setEndTime(day_end);
            expenseForm.setUserId(iaeForm.getUserId());
            List<FamExpense> expenses=famExpenseService.queryFamExpense(expenseForm);

            if (expenses.size()!=0){
                //每日消费
                Double i=0.0;
                AtomicReference<Double> expen = new AtomicReference<>(i);
                expenses.forEach(exp->{
                    expen.updateAndGet(v -> v + Double.parseDouble(exp.getFamExpenseAmount()));
                });
                IAEForm iae=new IAEForm();
                iae.setExpense(expen.get());
                bufgentMonth.updateAndGet(v -> v - expen.get());
                iae.setBudget(bufgentMonth.get());
                iae.setDate(date.toString("dd"));
                iaeForms.add(iae);
            }



        });


        return iaeForms;
    }

    @Override
    public List<IAEForm> getExpenseAndType(IAEForm iaeForm) {

        List<IAEForm> list=new ArrayList<>();
        Date begin = DateUtil.beginOfMonth(new Date());
        Date end = DateUtil.endOfMonth(new Date());
        FamExpenseForm expenseForm=new FamExpenseForm();
        expenseForm.setStartTime(begin);
        expenseForm.setEndTime(end);
        expenseForm.setUserId(iaeForm.getUserId());
        List<FamExpense> expenses=famExpenseService.queryFamExpense(expenseForm);

        if(expenses.size()!=0){
            Map<String, List<FamExpense>> collect = expenses.stream().collect(Collectors.groupingBy(FamExpense::getFamExpenseTypeId));
            collect.forEach((key,value)->{
                IAEForm iae=new IAEForm();
                iae.setBudgetTpye(key);
                Double i=0.0;
                AtomicReference<Double> expen = new AtomicReference<>(i);
                value.forEach(exp->{
                    expen.updateAndGet(v -> v + Double.parseDouble(exp.getFamExpenseAmount()));
                });
                iae.setExpense(expen.get());
                list.add(iae);
            });
        }


        return list;

    }
}
