package com.yyr.mapper;

import com.yyr.pojo.Family;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
* @description 针对表【family(家庭表)】的数据库操作Mapper
* @createDate 2022-11-29 12:15:47
* @Entity com.yyr.pojo.Family
*/
@Mapper
public interface FamilyMapper extends BaseMapper<Family> {

}




