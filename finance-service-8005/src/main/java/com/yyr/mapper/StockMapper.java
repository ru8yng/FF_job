package com.yyr.mapper;

import com.yyr.pojo.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
* @description 针对表【stock(收藏股票)】的数据库操作Mapper
* @createDate 2023-04-11 14:56:07
* @Entity com.yyr.pojo.Stock
*/
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

}




