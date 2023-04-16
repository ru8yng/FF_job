package com.yyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author 杨亚茹
 * @Date 2023/2/19 17:01
 * @PackageName:com.yyr
 * @ClassName: Common_Service8003Application
 * @Description: TODO
 * @Version 1.0
 */

@EnableDiscoveryClient
@SpringBootApplication
public class Common_Service8003Application {
    public static void main(String[] args) {
        SpringApplication.run(Common_Service8003Application.class, args);
    }

}
