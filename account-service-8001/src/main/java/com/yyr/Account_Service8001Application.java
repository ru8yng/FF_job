package com.yyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author 杨亚茹
 * @Date 2022/11/29 17:29
 * @PackageName:com.yyr
 * @ClassName: Account_Service8001Application
 * @Description: TODO
 * @Version 1.0
 */
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class Account_Service8001Application {
    public static void main(String[] args) {
        SpringApplication.run(Account_Service8001Application.class, args);
    }

}
