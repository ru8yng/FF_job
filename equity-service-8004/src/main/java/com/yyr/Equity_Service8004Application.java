package com.yyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author 杨亚茹
 * @Date 2023/3/2 19:24
 * @PackageName:com.yyr
 * @ClassName: Equity_Service8004Application
 * @Description: TODO
 * @Version 1.0
 */

@EnableDiscoveryClient
@SpringBootApplication
public class Equity_Service8004Application {
    public static void main(String[] args) {
        SpringApplication.run(Equity_Service8004Application.class, args);
    }

}
