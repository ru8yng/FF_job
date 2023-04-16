package com.yyr.mapper;

import com.yyr.pojo.IncomeType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
* @author sheep
* @description 针对表【income_type(收入类型)】的数据库操作Mapper
* @createDate 2023-03-04 15:00:00
* @Entity com.yyr.pojo.IncomeType
*/
@Mapper
public interface IncomeTypeMapper extends BaseMapper<IncomeType> {


}
