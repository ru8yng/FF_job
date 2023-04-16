package com.yyr.service;

import com.yyr.dto.FamAssetsForm;
import com.yyr.pojo.FamAssets;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【fam_assets(固定资产)】的数据库操作Service
* @createDate 2023-03-04 14:54:37
*/
public interface FamAssetsService extends IService<FamAssets> {
    void addAssets(FamAssetsForm form);

    void deleteAssets(String assId);

    void updateAssets(FamAssetsForm form);

    List<FamAssets> queryAssets(FamAssetsForm form);
}
