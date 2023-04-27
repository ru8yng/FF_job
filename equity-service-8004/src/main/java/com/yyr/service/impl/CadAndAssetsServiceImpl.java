package com.yyr.service.impl;

import cn.hutool.core.date.DateUtil;
import com.yyr.dto.BillsForm;
import com.yyr.dto.ClaimsAndDebtForm;
import com.yyr.dto.FamAssetsForm;
import com.yyr.pojo.ClaimsAndDebt;
import com.yyr.pojo.FamAssets;
import com.yyr.service.ClaimsAndDebtService;
import com.yyr.service.FamAssetsService;
import com.yyr.service.CadAndAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/24 17:31
 * @PackageName:com.yyr.service.impl
 * @ClassName: cadAndAssetsServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class CadAndAssetsServiceImpl implements CadAndAssetsService {
    @Autowired
    private ClaimsAndDebtService claimsAndDebtService;

    @Autowired
    private FamAssetsService famAssetsService;

    @Override
    public BillsForm queryBills(BillsForm form) {
        ClaimsAndDebtForm cadForm=new ClaimsAndDebtForm();
        FamAssetsForm assetsForm=new FamAssetsForm();
        Date begin=new Date();
        Date end=new Date();
        switch (form.getDate()){
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
        cadForm.setStartTime(begin);
        cadForm.setEndTime(end);
        assetsForm.setStartTime(begin);
        assetsForm.setEndTime(end);
        if (form.getFamId()!=null){
            //cadForm.setFamId(iae.getFamId());
            assetsForm.setFamId(form.getFamId());
            }
        List<ClaimsAndDebt> cads=new ArrayList<>();
        if(form.getUserId()!=null){
            cadForm.setObligor(form.getUserId());
            assetsForm.setCreatedBy(form.getUserId());
            cads.addAll(claimsAndDebtService.queryCAD(cadForm));
            cadForm.setObligor(form.getUserId());
            cads.addAll(claimsAndDebtService.queryCAD(cadForm));
        }

        List<FamAssets> assets=famAssetsService.queryAssets(assetsForm);
        form.setAssets(assets);
        form.setCads(cads);

        return form;
    }
}
