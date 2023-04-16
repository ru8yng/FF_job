package com.yyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author 杨亚茹
 * @Date 2023/3/2 19:33
 * @PackageName:com.yyr
 * @ClassName: Report_Service8006Application
 * @Description: TODO
 * @Version 1.0
 */

@EnableDiscoveryClient
@SpringBootApplication
public class Report_Service8006Application {
    public static void main(String[] args) {
        SpringApplication.run(Report_Service8006Application.class, args);
    }

}
