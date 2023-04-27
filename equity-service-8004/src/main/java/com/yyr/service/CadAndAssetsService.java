package com.yyr.service;

import com.yyr.dto.BillsForm;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/4/24 17:31
 * @PackageName:com.yyr.service
 * @ClassName: cadAndAssetsService
 * @Description: TODO
 * @Version 1.0
 */


public interface CadAndAssetsService {

    BillsForm queryBills(BillsForm form);
}
