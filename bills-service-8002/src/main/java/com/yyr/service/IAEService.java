package com.yyr.service;

import com.yyr.dto.FamIncomeForm;
import com.yyr.dto.IAE;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Author 杨亚茹
 * @Date 2023/3/28 15:56
 * @PackageName:com.yyr.service
 * @ClassName: IAEService
 * @Description: TODO
 * @Version 1.0
 */


public interface IAEService {

    IAE queryIaeBCurrentMonth();

}
