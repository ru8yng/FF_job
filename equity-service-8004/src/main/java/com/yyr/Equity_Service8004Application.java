package com.yyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 杨亚茹
 * @Date 2023/3/2 19:24
 * @PackageName:com.yyr
 * @ClassName: Equity_Service8004Application
 * @Description: TODO
 * @Version 1.0
 */
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Equity_Service8004Application {
    public static void main(String[] args) {
        SpringApplication.run(Equity_Service8004Application.class, args);
    }

}
