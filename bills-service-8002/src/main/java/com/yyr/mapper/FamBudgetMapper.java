package com.yyr.mapper;

import com.yyr.pojo.FamBudget;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
* @description 针对表【fam_budget(家庭预算)】的数据库操作Mapper
* @createDate 2023-03-28 15:49:21
* @Entity com.yyr.pojo.FamBudget
*/
@Mapper
public interface FamBudgetMapper extends BaseMapper<FamBudget> {

}




