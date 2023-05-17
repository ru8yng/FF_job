package com.yyr.service;


import bills8002.dto.IAEForm;

import java.util.List;

/**
 * @Author 杨亚茹
 * @Date 2023/3/28 15:56
 * @PackageName:com.yyr.service
 * @ClassName: IAEService
 * @Description: TODO
 * @Version 1.0
 */


public interface IAEService {

    IAEForm queryIaeCurrent(IAEForm iae);

    List<IAEForm> getExpenseBudgentLine(IAEForm iaeForm);

    List<IAEForm> getExpenseAndType(IAEForm iaeForm);

}
