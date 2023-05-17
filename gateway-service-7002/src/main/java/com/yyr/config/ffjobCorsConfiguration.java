package com.yyr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author 杨亚茹
 * @Date 2023/4/10 16:13
 * @PackageName:com.yyr.config
 * @ClassName: ffjobCorsConfiguration
 * @Description: TODO
 * @Version 1.0
 */

@Configuration
public class ffjobCorsConfiguration {
    @Bean
    public CorsFilter corsFilter(){
//        添加CORS配置信息，初始化配置对象
        CorsConfiguration config = new CorsConfiguration();
//        允许的域，写*则cookie无法使用，且所有域名都能够访问
        config.addAllowedOrigin("*");
//        config.addAllowedOrigin("http://www.xxx.com");
//        是否发送Cookie请求
        config.setAllowCredentials(true);
//        允许的请求方式,*代表全部允许
        config.addAllowedMethod("*");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PATCH");
//        允许的头信息 *表示全部允许
        config.addAllowedHeader("*");

//        添加映射路径，拦截一切请求（/**）
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**",config);
//        返回新的CorsFilter
        return  new CorsFilter(configSource);
    }
}
