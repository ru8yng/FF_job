package com.yyr.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @Title : RateLimiterAspect
 * @Package :
 * @Description :TODO（限流切面）
 * @Author:
 * @Date: 创建时间 2022-04-02
 */
@Slf4j(topic = "RateLimterHandler")
@Aspect
@Component
public class RateLimiterAspect {

    @Resource
    private RedisTemplate redisTemplate;

    @Qualifier("rateLimiterLua")
    @Resource
    private DefaultRedisScript luaRateLimiterScript;


    @Pointcut("@annotation(com.scjydz.config.RateLimiter)")
    public void rateLimiter() {

    }

    @Around("@annotation(rateLimiter)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, RateLimiter rateLimiter) throws Throwable {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        Signature signature = proceedingJoinPoint.getSignature();
        Assert.isTrue(signature instanceof MethodSignature,"the Annotation @RateLimter must used on method!");
        String ip = getIpAddress(httpServletRequest);
        // 限流模块key，限制每个ip的访问量
        String limitKey = rateLimiter.key() + ":" + ip;
        // url限制key   ，限制单位时间内总体的访问量
        String limitAllKey = rateLimiter.key();
        // 初始可存储数量
        long initTokens = rateLimiter.initTokens();
        // 桶的令牌上限
        long bucketMaxTokens = rateLimiter.bucketMaxTokens();
        // 桶的令牌上限 总体请求量的上限
        long maxBucketMaxTokens = rateLimiter.maxBucketMaxTokens();
        // 每个令牌产生的时间
        long intervalPerPermit = rateLimiter.intervalPerPermit();
        // 重置令牌桶时间间隔、30s 重置
        long resetBucketInterval = 30 * 1000;
        // 当前时间
        long curTime = System.currentTimeMillis();
        log.info("RateLimterHandler[分布式限流处理器], param-key={},initTokens={},bucketMaxTokens={},intervalPerPermit={}", limitKey, initTokens, bucketMaxTokens, intervalPerPermit);
        //执行Lua脚本
        Long result = (Long) redisTemplate.execute(luaRateLimiterScript, new ArrayList<>(Collections.singletonList(limitKey)), intervalPerPermit, curTime, bucketMaxTokens, bucketMaxTokens, resetBucketInterval);
        if (result == 0) {
            log.info("RateLimterHandler[分布式限流处理器] , key={},当前剩余令牌数：{} ,[触发单用户访问限流]", limitKey, result);
            Assert.isTrue(false,"服务繁忙！请稍后访问！");
        }
        Long result_all = (Long) redisTemplate.execute(luaRateLimiterScript, new ArrayList<>(Collections.singletonList(limitAllKey)), intervalPerPermit, curTime, maxBucketMaxTokens, maxBucketMaxTokens, resetBucketInterval);
        if (result_all == 0) {
            log.info("RateLimterHandler[分布式限流处理器] , key={}，剩余总令牌数：{} ,[触发总流量访问限流]", limitAllKey, result_all);
            Assert.isTrue(false,"服务繁忙！请稍后访问！");
        }
        log.info("RateLimterHandler[分布式限流处理器] , key={},当前剩余令牌数：{}，剩余总令牌数：{} ,请求[正常]响应",limitKey, result,result_all);
        return proceedingJoinPoint.proceed();
    }

    /**
     * 获取当前的request
     * 这里如果报空指针异常是因为单独使用spring获取request
     * 需要在配置文件里添加监听
     * <listener>
     * <listener-class>
     * org.springframework.web.context.request.RequestContextListener
     * </listener-class>
     * </listener>
     *
     * @return
     */
    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

