package com.yyr.config;

import java.lang.annotation.*;

/**
 * @Title : logCustom
 * @Package :com.yyr.config
 * @Description :TODO（记录日志信息）
 * @Author: yyr
 * @Date: 创建时间
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface logCustom {

    /**
     * 描述  日志的描述信息
     * 
     * @return {@link null  }
     * @throws
     * @author  yyr
     */
    String description() default "";

}
