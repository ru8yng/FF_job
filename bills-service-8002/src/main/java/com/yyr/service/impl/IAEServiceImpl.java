package com.yyr.service.impl;

import cn.hutool.core.date.DateUtil;
import com.yyr.dto.FamExpenseForm;
import com.yyr.dto.FamIncomeForm;
import com.yyr.dto.IAE;
import com.yyr.pojo.FamExpense;
import com.yyr.pojo.FamIncome;
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



    @Override
    public IAE queryIaeBCurrentMonth() {
        IAE iae=new IAE();
        FamIncomeForm form=new FamIncomeForm();
        FamExpenseForm form1=new FamExpenseForm();
        Date beginOfMon = DateUtil.beginOfMonth(new Date());
        Date endOfMon = DateUtil.endOfMonth(new Date());
        form.setStartTime(beginOfMon);
        form.setEndTime(endOfMon);
        form1.setStartTime(beginOfMon);
        form1.setEndTime(endOfMon);
        List<FamExpense> expenses=famExpenseService.queryFamExpense(form1);
        List<FamIncome> incomes=famIncomeService.queryFamIncome(form);

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

        //预算

        return iae;
    }
}
