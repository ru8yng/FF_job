package com.yyr.mapper;

import com.yyr.pojo.Fund;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
* @description 针对表【fund(收藏基金)】的数据库操作Mapper
* @createDate 2023-03-28 09:33:58
* @Entity com.yyr.pojo.Fund
*/
@Mapper
public interface FundMapper extends BaseMapper<Fund> {

}




