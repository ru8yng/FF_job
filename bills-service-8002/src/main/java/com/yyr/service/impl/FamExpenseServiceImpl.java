package com.yyr.service.impl;

import account8001.dto.UserQueryForm;
import bills8002.dto.FamExpenseForm;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyr.mapper.ExpensesTypeMapper;
import com.yyr.pojo.ExpensesType;
import com.yyr.pojo.FamExpense;
import com.yyr.service.AccountService8001;
import com.yyr.service.FamExpenseService;
import com.yyr.mapper.FamExpenseMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_expense(家庭支出)】的数据库操作Service实现
* @createDate 2023-03-04 14:59:12
*/
@Service
public class FamExpenseServiceImpl extends ServiceImpl<FamExpenseMapper, FamExpense>
implements FamExpenseService{

    @Autowired
    private FamExpenseMapper famExpenseMapper;

    @Autowired
    private ExpensesTypeMapper expensesTypeMapper;

    @Autowired
    private AccountService8001 accountService8001;

    /**
    * @description:新增家庭支出
    * @Param: [form]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void addFamExpense(FamExpenseForm form) {

        FamExpense famExpense=new FamExpense();
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            famExpense.setCreatedBy(form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            famExpense.setUpdatedBy(form.getUpdatedBy());
        }
        if(form.getUserId()!=null &&form.getUserId().length()!=0){
            famExpense.setUserId(form.getUserId());
        }
        if(form.getExpenseTime()!=null){
            famExpense.setExpenseTime(form.getExpenseTime());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            famExpense.setFamId(form.getFamId());
        }
        if(form.getFamExpenseAmount()!=null &&form.getFamExpenseAmount().length()!=0){

            famExpense.setFamExpenseAmount(form.getFamExpenseAmount());
        }
        if(form.getFamExpenseTypeId()!=null &&form.getFamExpenseTypeId().length()!=0){
            famExpense.setFamExpenseTypeId(form.getFamExpenseTypeId());
        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            famExpense.setRemark(form.getRemark());
        }

        famExpenseMapper.insert(famExpense);

    }

    /**
    * @description:根据id删除家庭支出
    * @Param: [famExpenseId]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void deleteFamExpense(String famExpenseId) {
        FamExpense famExpense=famExpenseMapper.selectById(famExpenseId);
        Assert.notNull(famExpense,"该家庭支出不存在");
        this.removeById(famExpenseId);

    }

    /**
    * @description:更新家庭支出
    * @Param: [form]
    * @return: void
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public void updateFamExpense(FamExpenseForm form) {
        FamExpense famExpense=famExpenseMapper.selectById(form.getFamExpenseId());
        Assert.notNull(famExpense,"该家庭支出不存在");
        LambdaUpdateWrapper<FamExpense> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(FamExpense::getFamExpenseId,form.getFamExpenseId());
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            updateWrapper.set(FamExpense::getCreatedBy,form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            updateWrapper.set(FamExpense::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getUserId()!=null &&form.getUserId().length()!=0){
            updateWrapper.set(FamExpense::getUserId,form.getUserId());
        }
        if(form.getExpenseTime()!=null){
            updateWrapper.set(FamExpense::getExpenseTime,form.getExpenseTime());
        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            updateWrapper.set(FamExpense::getFamId,form.getFamId());
        }
        if(form.getFamExpenseAmount()!=null &&form.getFamExpenseAmount().length()!=0){
            updateWrapper.set(FamExpense::getFamExpenseAmount,form.getFamExpenseAmount());
        }
        if(form.getFamExpenseTypeId()!=null &&form.getFamExpenseTypeId().length()!=0){
            updateWrapper.set(FamExpense::getFamExpenseTypeId,form.getFamExpenseTypeId());
        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            updateWrapper.set(FamExpense::getRemark,form.getRemark());
        }

        this.update(updateWrapper);

    }

    /**
    * @description:查询家庭支出
    * @Param: [form]
    * @return: java.util.List<com.yyr.pojo.FamExpense>
    * @throws:
    * @author:杨亚茹
    */
    @Override
    public List<FamExpense> queryFamExpense(FamExpenseForm form) {
        LambdaQueryWrapper<FamExpense> queryWrapper=new LambdaQueryWrapper<>();

        if(form.getFamExpenseId()!=null &&form.getFamExpenseId().length()!=0){
            queryWrapper.eq(FamExpense::getFamExpenseId,form.getFamExpenseId());
        }
        if(form.getCreatedBy()!=null &&form.getCreatedBy().length()!=0){
            queryWrapper.eq(FamExpense::getCreatedBy,form.getCreatedBy());
        }
        if(form.getUpdatedBy()!=null &&form.getUpdatedBy().length()!=0){
            queryWrapper.eq(FamExpense::getUpdatedBy,form.getUpdatedBy());
        }
        if(form.getUserId()!=null &&form.getUserId().length()!=0){
            queryWrapper.eq(FamExpense::getUserId,form.getUserId());
        }
//        if(form.getExpenseTime()!=null){
//            queryWrapper.eq(FamExpense::getExpenseTime,form.getExpenseTime());
//        }
        if(form.getFamId()!=null &&form.getFamId().length()!=0){
            queryWrapper.eq(FamExpense::getFamId,form.getFamId());
        }
        if(form.getFamExpenseAmount()!=null &&form.getFamExpenseAmount().length()!=0){

            queryWrapper.eq(FamExpense::getFamExpenseAmount,form.getFamExpenseAmount());
        }
        if(form.getFamExpenseTypeId()!=null &&form.getFamExpenseTypeId().length()!=0){
            queryWrapper.eq(FamExpense::getFamExpenseTypeId,form.getFamExpenseTypeId());
        }
        if(form.getRemark()!=null &&form.getRemark().length()!=0){
            queryWrapper.like(FamExpense::getRemark,form.getRemark());
        }

        if(form.getStartTime()!=null &&form.getEndTime()!=null){
            queryWrapper.between(FamExpense::getExpenseTime,form.getStartTime(),form.getEndTime());
        }
        List<FamExpense> famExpenses=this.list(queryWrapper);
        for (FamExpense famExpense : famExpenses) {
            famExpense.setFamExpenseTypeId(expensesTypeMapper.selectById(famExpense.getFamExpenseTypeId()).getExpenseTypeName());
            UserQueryForm userQueryForm=new UserQueryForm();
            userQueryForm.setUserId(famExpense.getUserId());
            List<UserQueryForm> list= Convert.convert(new TypeReference<List<UserQueryForm>>(){},accountService8001.queryUserList(userQueryForm).getData());
            famExpense.setUserId(list.get(0).getUserName());
        }

        return famExpenses;
    }

}
