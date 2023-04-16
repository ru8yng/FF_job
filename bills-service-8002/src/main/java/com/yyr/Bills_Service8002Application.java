package com.yyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 杨亚茹
 * @Date 2023/3/2 19:00
 * @PackageName:com.yyr
 * @ClassName: Bills_Service8002Application
 * @Description: TODO
 * @Version 1.0
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Bills_Service8002Application {
    public static void main(String[] args) {
        SpringApplication.run(Bills_Service8002Application.class, args);
    }
}
