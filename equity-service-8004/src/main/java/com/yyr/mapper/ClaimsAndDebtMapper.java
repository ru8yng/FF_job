package com.yyr.mapper;

import com.yyr.pojo.ClaimsAndDebt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
* @author sheep
* @description 针对表【claims_and_debt(借贷)】的数据库操作Mapper
* @createDate 2023-03-04 15:03:23
* @Entity com.yyr.pojo.ClaimsAndDebt
*/
@Mapper
public interface ClaimsAndDebtMapper extends BaseMapper<ClaimsAndDebt> {


}
