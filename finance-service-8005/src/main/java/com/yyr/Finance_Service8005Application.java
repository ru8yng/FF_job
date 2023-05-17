package com.yyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author 杨亚茹
 * @Date 2023/3/2 19:30
 * @PackageName:com.yyr
 * @ClassName: Finance_ServiceApplication
 * @Description: TODO
 * @Version 1.0
 */
@EnableFeignClients
@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
public class Finance_Service8005Application {
    public static void main(String[] args) {
        SpringApplication.run(Finance_Service8005Application.class, args);
    }

}
