package com.yyr.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @Author 杨亚茹
 * @Date 2022/11/29 12:18
 * @PackageName:com.yyr.config
 * @ClassName: MyBatisPlusMetaObjectHandle
 * @Description: TODO
 * @Version 1.0
 */

@Component
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时候的填充策略
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createdTime",new Date(),metaObject);
        this.setFieldValByName("updatedTime",new Date(),metaObject);
    }

    /**
     * 更新时候的填充策略
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedTime",new Date(),metaObject);
    }
}