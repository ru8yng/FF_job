package com.yyr.mapper;

import com.yyr.pojo.FamAssets;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
* @description 针对表【fam_assets(固定资产)】的数据库操作Mapper
* @createDate 2023-03-04 14:54:37
* @Entity com.yyr.pojo.FamAssets
*/
@Mapper
public interface FamAssetsMapper extends BaseMapper<FamAssets> {


}
