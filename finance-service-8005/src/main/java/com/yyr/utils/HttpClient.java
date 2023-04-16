package com.yyr.utils;


import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Slf4j(topic = "httpClinet")
public class HttpClient {
    public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(10000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            } else {

                System.out.print(connection.getResponseCode());
                System.out.print(connection.getResponseMessage());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.toString());
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }

    public static String doPost(String httpUrl, Map<String, List<String>> headers, String param) {
        long start = System.currentTimeMillis();
        log.info(" doPost url=>{} ,\r\n param=>{} ", httpUrl, param);
        String result = "";
        try {
            result = HttpUtil.createGet(httpUrl)
                    .timeout(10 * 1000)
                    .header(headers)
                    .body(param)
                    .execute()
                    .body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(" doPost url=>{} ,耗时:{} ms ,\r\n return {} ", httpUrl, System.currentTimeMillis() - start, result);
        return result;
    }

    public static String doPost(String httpUrl, String param) {
        return doPost(httpUrl, null, param);
    }


    public static String doGet(String httpUrl, Map<String, List<String>> headers, Map<String, Object> form) {
        long start = System.currentTimeMillis();
        log.info(" doPost url=>{} ,\r\n param=>{} ", httpUrl, form);
        String result = "";
        try {
            result = HttpUtil.createPost(httpUrl)
                    .timeout(60 * 1000)
                    .header(headers)
                    .form(form)
                    .execute()
                    .body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(" doPost url=>{} ,耗时:{} ms ,\r\n return {} ", httpUrl, System.currentTimeMillis() - start, result);
        return result;
    }

    public static HttpResponse doPostForm(String httpUrl, Map<String, List<String>> headers, Map<String, Object> param) {
        long start = System.currentTimeMillis();
        log.info(" doPost url=>{} ,\r\n param=>{} ", httpUrl, param);
        HttpResponse result = null;
        try {
            result = HttpUtil.createPost(httpUrl)
                    .timeout(10 * 1000)
                    .header(headers)
                    .form(param)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(" doPost url=>{} ,耗时:{} ms ,\r\n return {} ", httpUrl, System.currentTimeMillis() - start, result);
        return result;
    }

    public static String doPostFormFile(String httpUrl, Map<String, List<String>> header, Map<String, Object> params, String paramFileName, File file) {
        long start = System.currentTimeMillis();
        log.info(" doPost url=>{} ,\r\n param=>{} ", httpUrl, params);
        String result = null;
        try {
            result = HttpUtil.createPost(httpUrl)
                    .timeout(2000)
                    .header(header)
                    .form(paramFileName, file)
                    .form(params)
                    .execute()
                    .body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(" doPost url=>{} ,耗时:{} ms ,\r\n return {} ", httpUrl, System.currentTimeMillis() - start, result);
        return result;
    }
}
