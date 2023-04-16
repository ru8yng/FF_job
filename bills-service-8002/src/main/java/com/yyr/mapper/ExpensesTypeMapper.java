package com.yyr.mapper;

import com.yyr.pojo.ExpensesType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
* @description 针对表【expenses_type(支出类型)】的数据库操作Mapper
* @createDate 2023-03-04 14:58:41
* @Entity com.yyr.pojo.ExpensesType
*/
@Mapper
public interface ExpensesTypeMapper extends BaseMapper<ExpensesType> {


}
