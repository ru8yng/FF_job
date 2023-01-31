package com.yyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author 杨亚茹
 * @Date 2022/9/23 11:41
 * @PackageName:com.yyr_job
 * @ClassName: EurekaServer_7001
 * @Description: TODO
 * @Version 1.0
 */

@SpringBootApplication
//服务端的启动类，可以接受别人注册进来
@EnableEurekaServer
public class EurekaServer_7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_7001.class,args);
    }
}
