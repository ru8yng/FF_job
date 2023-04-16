package com.yyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author 杨亚茹
 * @Date 2023/4/10 16:06
 * @PackageName:com.yyr
 * @ClassName: FF_jobGateWayApplication
 * @Description: TODO
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class FF_jobGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FF_jobGateWayApplication.class,args);
    }

}
