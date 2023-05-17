package com.yyr.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import com.yyr.pojo.ClaimsAndDebt;
import com.yyr.pojo.FamAssets;
import com.yyr.service.ClaimsAndDebtService;
import com.yyr.service.FamAssetsService;
import com.yyr.service.CadAndAssetsService;
import equity8004.dto.AcdaForm;
import equity8004.dto.ClaimsAndDebtForm;
import equity8004.dto.FamAssetsForm;
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
    public AcdaForm queryAcad(AcdaForm form) {
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

        if(form.getUserId()!=null){
            cadForm.setCreatedBy(form.getUserId());
            assetsForm.setCreatedBy(form.getUserId());

        }
        List<ClaimsAndDebt> cads=claimsAndDebtService.queryCAD(cadForm);
        List<FamAssets> assets=famAssetsService.queryAssets(assetsForm);
        List<ClaimsAndDebtForm> claimsAndDebtForms= Convert.convert(new TypeReference<List<ClaimsAndDebtForm>>(){},cads);

        List<FamAssetsForm> assetsForms= Convert.convert(new TypeReference<List<FamAssetsForm>>(){},assets);
        form.setAssets(assetsForms);
        form.setCads(claimsAndDebtForms);

        return form;
    }
}
