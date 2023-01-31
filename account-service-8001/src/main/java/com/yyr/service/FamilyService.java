package com.yyr.service;

import com.yyr.dto.FamQueryForm;
import com.yyr.pojo.Family;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author sheep
* @description 针对表【family(家庭表)】的数据库操作Service
* @createDate 2022-11-29 12:15:47
*/
public interface FamilyService extends IService<Family> {

    void addFamily(Family fm);

    void deleteFamily(String id);

    void updateFamily(Family fm);

    List<Family> queryList(FamQueryForm form);

    void enableFamily(FamQueryForm form);

}
