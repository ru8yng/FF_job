package com.yyr.mapper;

import com.yyr.pojo.FamIncome;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
* @author sheep
* @description 针对表【fam_income(家庭收入)】的数据库操作Mapper
* @createDate 2023-03-04 14:59:35
* @Entity com.yyr.pojo.FamIncome
*/
@Mapper
public interface FamIncomeMapper extends BaseMapper<FamIncome> {


}
