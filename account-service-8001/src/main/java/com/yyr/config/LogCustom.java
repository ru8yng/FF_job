package com.yyr.config;

import java.lang.annotation.*;

/**
 * @Author 杨亚茹
 * @Date 2023/2/19 15:43
 * @PackageName:com.yyr.config
 * @ClassName: logCustom
 * @Description: TODO
 * @Version 1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogCustom {
    /**
     * 描述  日志的描述信息
     *
     * @return {@link null  }
     * @throws
     * @author 刘涛
     */
    String description() default "";
}
