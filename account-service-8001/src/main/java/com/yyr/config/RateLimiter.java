package com.yyr.config;

import java.lang.annotation.*;

/**
 * @Title : RateLimiter
 * @Package :com.example.demo3.config
 * @Description :TODO（限流annotaion）
 * @Author: 刘涛
 * @Date: 创建时间 2022-04-02
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    /**
     * 限流key
     * @return
     */
    String key() default "rate:limiter";
    /**
     * 初始可存储数量
     * @return
     */
    long initTokens() default 10;

    /**
     *  桶的令牌上限
     * @return
     */
    long bucketMaxTokens() default 1000;

    /**
     *  桶的总访问上限
     * @return
     */
    long maxBucketMaxTokens() default 5000;

    /**
     * 每个令牌产生的时间间隔
     * @return
     */
    long intervalPerPermit() default 100;

}
