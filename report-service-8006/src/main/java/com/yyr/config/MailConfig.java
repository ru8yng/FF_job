package com.yyr.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.netflix.discovery.converters.Auto;
import com.yyr.dto.*;
import com.yyr.service.account_service_8001;
import com.yyr.service.bills_service_8002;
import com.yyr.service.equity_service_8004;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author 杨亚茹
 * @Date 2023/4/20 21:23
 * @PackageName:com.yyr.controller
 * @ClassName: MailController
 * @Description: TODO
 * @Version 1.0
 */

@Component
public class MailConfig {
    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    private account_service_8001 service8001;

    @Autowired
    private bills_service_8002 service8002;

    @Autowired
    private equity_service_8004 service8004;

    @Scheduled(cron = "0 30 22 31 12 ? ")  //每年12月2号22点30分00秒触发任务
    @Bean
    public String senderMailYearly() {

        List<User> userList= Convert.convert(new TypeReference<List<User>>() {}, service8001.queryUserList(new UserQueryForm()).getData());
        Map<String,List<User>> famEmails=userList.stream().collect(Collectors.groupingBy(User::getFamilyId));

        for(List<User> users:famEmails.values()){
            users.forEach(user->{
                IAEForm iae=new IAEForm();
                iae.setFamId(user.getFamilyId());
                iae.setUserId(user.getUserId());
                iae.setDate("year");

                BillsForm bill=new BillsForm();
                bill.setFamId(user.getFamilyId());
                bill.setUserId(user.getUserId());
                bill.setDate("year");

                IAEForm iaes= Convert.convert(IAEForm.class,service8002.queryIaeCurrent(iae).getData());
                BillsForm bills= Convert.convert(BillsForm.class,service8004.queryBills(bill).getData()) ;


                SimpleMailMessage message = new SimpleMailMessage();
                // 发件人 你的邮箱
                message.setFrom("572174279@qq.com");
                // 接收人 接收者邮箱
                message.setTo(user.getEmail());

                //邮件标题
                message.setText(
                        "您本年支出为:"+iaes.getExpense()+"\n"
                        +"您本年收入为:"+iaes.getIncome()+"\n"
                        +"您本年预算为:"+iaes.getBudget()+"\n"
                        +"您本年增加的固定资产有:"+bills.getAssets().stream().map(FamAssets::getAssetsName).collect(Collectors.toList()).toString()+"\n"
                        +"本年与您相关的借贷信息有:"+bills.getCads().toString()+"\n"
                );
                //收入支出预算

                //

                message.setSubject("大吉大利！");
                javaMailSender.send(message);
            });
        }

        return "success!";
    }

    @Scheduled(cron = "0 30 22 29 * ?")  //每月22点30分00秒触发任务
    //@Scheduled(cron = "0 */10 * * * ? ")//每隔10分钟执行一次
    @Bean
    public String senderMailMonthly() {

        List<User> userList= Convert.convert(new TypeReference<List<User>>() {}, service8001.queryUserList(new UserQueryForm()).getData());
        Map<String,List<User>> famEmails=userList.stream().collect(Collectors.groupingBy(User::getFamilyId));

        for(List<User> users:famEmails.values()){
            users.forEach(user->{
                IAEForm iae=new IAEForm();
                iae.setFamId(user.getFamilyId());
                iae.setUserId(user.getUserId());
                iae.setDate("month");

                BillsForm bill=new BillsForm();
                bill.setFamId(user.getFamilyId());
                bill.setUserId(user.getUserId());
                bill.setDate("month");

                IAEForm iaes= Convert.convert(IAEForm.class,service8002.queryIaeCurrent(iae).getData());
                BillsForm bills= Convert.convert(BillsForm.class,service8004.queryBills(bill).getData()) ;


                SimpleMailMessage message = new SimpleMailMessage();
                // 发件人 你的邮箱
                message.setFrom("572174279@qq.com");
                // 接收人 接收者邮箱
                message.setTo(user.getEmail());

                //邮件标题
                message.setText(
                        "您本月支出为"+iaes.getExpense()+"\n"
                                +"您本月收入为:"+iaes.getIncome()+"\n"
                                +"您本月预算为:"+iaes.getBudget()+"\n"
                                +"您本月增加的固定资产有:"+bills.getAssets().stream().map(FamAssets::getAssetsName).collect(Collectors.toList()).toString()+"\n"
                                +"本月与您相关的借贷信息有:"+bills.getCads().toString()+"\n"
                );
                //收入支出预算

                //

                message.setSubject("大吉大利！");
                javaMailSender.send(message);
            });
        }

        return "success!";
    }

    @Scheduled(cron = "0 30 22 ? * FRI")  //每年12月2号22点30分00秒触发任务
    @Bean
    public String senderMailWeekly() {

        List<User> userList= Convert.convert(new TypeReference<List<User>>() {}, service8001.queryUserList(new UserQueryForm()).getData());
        Map<String,List<User>> famEmails=userList.stream().collect(Collectors.groupingBy(User::getFamilyId));

        for(List<User> users:famEmails.values()){
            users.forEach(user->{
                IAEForm iae=new IAEForm();
                iae.setFamId(user.getFamilyId());
                iae.setUserId(user.getUserId());
                iae.setDate("week");

                BillsForm bill=new BillsForm();
                bill.setFamId(user.getFamilyId());
                bill.setUserId(user.getUserId());
                bill.setDate("week");

                IAEForm iaes= Convert.convert(IAEForm.class,service8002.queryIaeCurrent(iae).getData());
                BillsForm bills= Convert.convert(BillsForm.class,service8004.queryBills(bill).getData()) ;


                SimpleMailMessage message = new SimpleMailMessage();
                // 发件人 你的邮箱
                message.setFrom("572174279@qq.com");
                // 接收人 接收者邮箱
                message.setTo(user.getEmail());

                //邮件标题
                message.setText(
                        "您本周支出为"+iaes.getExpense()+"\n"
                                +"您本周收入为:"+iaes.getIncome()+"\n"
                                +"您本周预算为:"+iaes.getBudget()+"\n"
                                +"您本周增加的固定资产有:"+bills.getAssets().stream().map(FamAssets::getAssetsName).collect(Collectors.toList()).toString()+"\n"
                                +"本年周您相关的借贷信息有:"+bills.getCads().toString()+"\n"
                );
                //收入支出预算

                //

                message.setSubject("大吉大利！");
                javaMailSender.send(message);
            });
        }

        return "success!";
    }

}
