package com.yyr.config;

import account8001.dto.UserQueryForm;
import bills8002.dto.IAEForm;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.yyr.service.account_service_8001;
import com.yyr.service.bills_service_8002;
import com.yyr.service.equity_service_8004;
import com.yyr.service.finance_service_8005;
import equity8004.dto.AcdaForm;
import finance8005.dto.FundAndStockForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private finance_service_8005 service8005;


    @Scheduled(cron = "0 30 22 31 12 ? ")  //每年12月2号22点30分00秒触发任务
    @Bean
    public String senderMailYearly() {

        List<UserQueryForm> userList1= Convert.convert(new TypeReference<List<UserQueryForm>>() {}, service8001.queryUserList1(new UserQueryForm()).getData());
        List<UserQueryForm> userList=userList1.stream().filter(user->user.getEmail()!=null && user.getEmail().length()!=0).collect(Collectors.toList());
        Map<String,List<UserQueryForm>> famEmails=userList.stream().collect(Collectors.groupingBy(UserQueryForm::getFamilyId));

        for(List<UserQueryForm> users:famEmails.values()){
            users.forEach(user->{
                IAEForm iae=new IAEForm();
                iae.setFamId(user.getFamilyId());
                iae.setUserId(user.getUserId());
                iae.setDate("year");

                AcdaForm acad=new AcdaForm();
                acad.setFamId(user.getFamilyId());
                acad.setUserId(user.getUserId());
                acad.setDate("year");

                FundAndStockForm form=new FundAndStockForm();
                form.setUserId(user.getUserId());
                form.setStockFormList(new ArrayList<>());
                form.setFundFormList(new ArrayList<>());
                IAEForm iaes= Convert.convert(IAEForm.class,service8002.queryIaeCurrent(iae).getData());
                AcdaForm acadForm= Convert.convert(AcdaForm.class,service8004.queryAcad(acad).getData()) ;
                FundAndStockForm finances=Convert.convert(FundAndStockForm.class,service8005.queryProfits(form).getData());

                StringBuffer htmlhead=new StringBuffer("<!DOCTYPE html>\n" +
                        "                         <html>\n" +
                        "                         <head>\n" +
                        "                         <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "                         <link rel=\"stylesheet\" type=\"text/css\" id=\"u0\" href=\"https://zh.rakko.tools/tools/129/lib/tinymce/skins/ui/oxide/content.min.css\">\n" +
                        "                         <link rel=\"stylesheet\" type=\"text/css\" id=\"u1\" href=\"https://zh.rakko.tools/tools/129/lib/tinymce/skins/content/default/content.min.css\">\n" +
                        "                         </head>\n" +
                        "                         <body id=\"tinymce\" class=\"mce-content-body \" data-id=\"content\" contenteditable=\"true\" spellcheck=\"false\">\n" +
                        "                         <p>您的年报已送达！\uD83D\uDE00</p>"+
                        "                         <p>&nbsp; &nbsp; &nbsp;  \uD83D\uDE06您今年的消费是："+iaes.getExpense()+"元；</p>\n" +
                        "                         <p>&nbsp; &nbsp; &nbsp;  \uD83E\uDD29您今年的收入是："+iaes.getIncome()+"元；</p>\n" +
                        "                         <p>&nbsp; &nbsp; &nbsp;  \uD83E\uDD13与您有关的固定资产有：</p>\n" +
                        "                 <table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n" +
                        "                 <tbody>\n" +
                        "                 <tr>\n" +
                        "                 <td style=\"width: 14.2857%;\">资产名称</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">资产地点</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">购买时间</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">资产分期(0为不分期1为分期)</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">剩余期限/月</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">每期还款金额(元)</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">备注</td></tr>\n");
                StringBuffer assets= new StringBuffer();
                if(acadForm.getAssets()!=null){
                    acadForm.getAssets().forEach(asset->{
                        String assethtml="<tr>\n"+
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsName()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsLocation()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsBuytime()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsInstalment()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getInstalmentSurplus()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getInstalmentSurplus()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getRemark()+"</td>\n" +
                                "</tr>\n";
                        assets.append(assethtml);
                    });

                }
                htmlhead.append(assets);
                htmlhead.append("</tbody></table>");
                htmlhead.append(" <p>&nbsp; &nbsp; &nbsp;  \uD83E\uDD2D与您有关的借贷有：</p><table style=\"border-collapse: collapse; width: 100%;\" border=\"1\"><tbody><tr><td style=\"width: 12.5%;\">是否完成</td><td style=\"width: 12.5%;\">债权人</td><td style=\"width: 12.5%;\">债务人</td><td style=\"width: 12.5%;\">债权人联系方式</td><td style=\"width: 12.5%;\">债务人联系方式</td><td style=\"width: 12.5%;\">借贷金额</td><td style=\"width: 12.5%;\">借入/借出时间</td><td style=\"width: 12.5%;\">备注</td></tr>");
                StringBuffer cads=new StringBuffer();
                if (acadForm.getCads()!=null){
                    acadForm.getCads().forEach(cad->{
                        String cadhtml=" <tr>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCadStatus()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCreditor()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getObligor()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCreditorTel()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getObligorTel()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCadAmount()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCadTime()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getRemark()+"</td>\n" +
                                "                 </tr>";
                        cads.append(cadhtml);
                    });
                }
                htmlhead.append(cads);
                htmlhead.append("</tbody></table>");
                htmlhead.append(" <p>&nbsp; &nbsp; &nbsp;  \uD83D\uDE18您到目前为止基金收入情况是：</p>\n" +
                        "                 <table style=\"border-collapse: collapse; width: 100%;\" border=\"1\" data-mce-style=\"border-collapse: collapse; width: 100%;\">\n" +
                        "                 <tbody>\n" +
                        "                 <tr>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">基金代码</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">基金名</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">购买时间</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">当前收益(元)</td>\n" +
                        "                 </tr>");
                //填充数据
                StringBuffer funds=new StringBuffer();
                if (finances.getFundFormList()!=null){
                    finances.getFundFormList().forEach(fund->{
                        String fundhtml=" <tr>\n" +
                                "                  <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getFundCode()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getFundName()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getCreatedTime()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getCurrentProfit()+"</td>" +
                                "                 </tr>";
                        funds.append(fundhtml);
                    });
                }
                htmlhead.append(funds);
                htmlhead.append("</tbody></table>");
                htmlhead.append(" <p>&nbsp; &nbsp; &nbsp; \uD83D\uDE0E您到目前为止股票收入情况是：</p>\n" +
                        "                 <table style=\"border-collapse: collapse; width: 100%;\" border=\"1\" data-mce-style=\"border-collapse: collapse; width: 100%;\">\n" +
                        "                 <tbody>\n" +
                        "                 <tr>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">股票代码</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">股票名</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">购买时间</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">当前收益(元)</td>\n" +
                        "                 </tr>");
                StringBuffer stocks=new StringBuffer();
                if (finances.getStockFormList()!=null){
                    finances.getStockFormList().forEach(stock->{
                        String stockhtml=" <tr>\n" +
                                "                  <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getStockCode()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getStockName()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getCreatedTime()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getCurrentProfit()+"</td>" +
                                "                 </tr>";
                        stocks.append(stockhtml);
                    });
                }
                htmlhead.append(stocks);
                htmlhead.append("</tbody></table></body>");
                htmlhead.append(" <div data-row=\"0\" role=\"presentation\" class=\"ephox-snooker-resizer-rows ephox-snooker-resizer-bar\" style=\"position: absolute; left: 16px; top: 373.833px; height: 7px; width: 939px;\"></div>\n" +
                        "                 <div data-row=\"1\" role=\"presentation\" class=\"ephox-snooker-resizer-rows ephox-snooker-resizer-bar\" style=\"position: absolute; left: 16px; top: 409.687px; height: 7px; width: 939px;\"></div>\n" +
                        "                 <div data-column=\"0\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 130.083px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"1\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 247.333px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"2\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 364.583px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"3\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 481.833px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"4\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 599.083px; top: 318.75px; height: 94.7708px; width: 7px;\"></div><div data-column=\"5\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 716.333px; top: 318.75px; height: 94.7708px; width: 7px;\"></div><div data-column=\"6\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 833.583px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"7\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 950.583px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 </html>\n");
                MimeMessagePreparator message= mimeMessage -> {
                    mimeMessage.setRecipient(Message.RecipientType.TO,

                            new InternetAddress(user.getEmail()));//收件人

                    mimeMessage.setFrom(new InternetAddress("572174279@qq.com"));//发件人

                    mimeMessage.setSubject("大吉大利！");//邮件标题

                    mimeMessage.setSentDate(new Date());//发送时间

                    mimeMessage.setContent(htmlhead.toString(),"text/html;charset=utf-8");// 网页格式
                };
                javaMailSender.send(message);
            });
        }


        return "success!";
    }

    @Scheduled(cron = "0 30 22 29 * ?")  //每月22点30分00秒触发任务
    //@Scheduled(cron = "0 */10 * * * ? ")//每隔10分钟执行一次
    @Bean
    public String senderMailMonthly() {

        List<UserQueryForm> userList1= Convert.convert(new TypeReference<List<UserQueryForm>>() {}, service8001.queryUserList1(new UserQueryForm()).getData());
        List<UserQueryForm> userList=userList1.stream().filter(user->user.getEmail()!=null && user.getEmail().length()!=0).collect(Collectors.toList());
        Map<String,List<UserQueryForm>> famEmails=userList.stream().collect(Collectors.groupingBy(UserQueryForm::getFamilyId));

        for(List<UserQueryForm> users:famEmails.values()){
            users.forEach(user->{
                IAEForm iae=new IAEForm();
                iae.setFamId(user.getFamilyId());
                iae.setUserId(user.getUserId());
                iae.setDate("month");

                AcdaForm acad=new AcdaForm();
                acad.setFamId(user.getFamilyId());
                acad.setUserId(user.getUserId());
                acad.setDate("month");

                FundAndStockForm form=new FundAndStockForm();
                form.setUserId(user.getUserId());

                IAEForm iaes= Convert.convert(IAEForm.class,service8002.queryIaeCurrent(iae).getData());
                AcdaForm acadForm= Convert.convert(AcdaForm.class,service8004.queryAcad(acad).getData()) ;
                FundAndStockForm finances=Convert.convert(FundAndStockForm.class,service8005.queryProfits(form).getData());

                StringBuffer htmlhead=new StringBuffer("<!DOCTYPE html>\n" +
                        "                         <html>\n" +
                        "                         <head>\n" +
                        "                         <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "                         <link rel=\"stylesheet\" type=\"text/css\" id=\"u0\" href=\"https://zh.rakko.tools/tools/129/lib/tinymce/skins/ui/oxide/content.min.css\">\n" +
                        "                         <link rel=\"stylesheet\" type=\"text/css\" id=\"u1\" href=\"https://zh.rakko.tools/tools/129/lib/tinymce/skins/content/default/content.min.css\">\n" +
                        "                         </head>\n" +
                        "                         <body id=\"tinymce\" class=\"mce-content-body \" data-id=\"content\" contenteditable=\"true\" spellcheck=\"false\">\n" +
                        "                         <p>您的月报已送达！\uD83D\uDE00</p>"+
                        "                         <p>&nbsp; &nbsp; &nbsp;  \uD83D\uDE06您这个月的消费是："+iaes.getExpense()+"元；</p>\n" +
                        "                         <p>&nbsp; &nbsp; &nbsp;  \uD83E\uDD29您这个月的收入是："+iaes.getIncome()+"元；</p>\n" +
                        "                         <p>&nbsp; &nbsp; &nbsp;  \uD83E\uDD13与您有关的固定资产有：</p>\n" +
                                "                 <table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n" +
                                "                 <tbody>\n" +
                                "                 <tr>\n" +
                                "                 <td style=\"width: 14.2857%;\">资产名称</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">资产地点</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">购买时间</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">资产分期(0为不分期1为分期)</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">剩余期限/月</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">每期还款金额(元)</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">备注</td></tr>\n");
                StringBuffer assets= new StringBuffer();
                if(acadForm.getAssets()!=null){
                    acadForm.getAssets().forEach(asset->{
                        String assethtml="<tr>\n"+
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsName()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsLocation()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsBuytime()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsInstalment()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getInstalmentSurplus()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getInstalmentSurplus()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getRemark()+"</td>\n" +
                                "</tr>\n";
                        assets.append(assethtml);
                    });

                }
                htmlhead.append(assets);
                htmlhead.append("</tbody></table>");
                htmlhead.append(" <p>&nbsp; &nbsp; &nbsp;  \uD83E\uDD2D与您有关的借贷有：</p><table style=\"border-collapse: collapse; width: 100%;\" border=\"1\"><tbody><tr><td style=\"width: 12.5%;\">是否完成</td><td style=\"width: 12.5%;\">债权人</td><td style=\"width: 12.5%;\">债务人</td><td style=\"width: 12.5%;\">债权人联系方式</td><td style=\"width: 12.5%;\">债务人联系方式</td><td style=\"width: 12.5%;\">借贷金额</td><td style=\"width: 12.5%;\">借入/借出时间</td><td style=\"width: 12.5%;\">备注</td></tr>");
                StringBuffer cads=new StringBuffer();
                if (acadForm.getCads()!=null){
                    acadForm.getCads().forEach(cad->{
                        String cadhtml=" <tr>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCadStatus()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCreditor()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getObligor()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCreditorTel()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getObligorTel()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCadAmount()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCadTime()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getRemark()+"</td>\n" +
                                "                 </tr>";
                        cads.append(cadhtml);
                    });
                }
                htmlhead.append(cads);
                htmlhead.append("</tbody></table>");
                htmlhead.append(" <p>&nbsp; &nbsp; &nbsp;  \uD83D\uDE18您到目前为止基金收入情况是：</p>\n" +
                        "                 <table style=\"border-collapse: collapse; width: 100%;\" border=\"1\" data-mce-style=\"border-collapse: collapse; width: 100%;\">\n" +
                        "                 <tbody>\n" +
                        "                 <tr>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">基金代码</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">基金名</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">购买时间</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">当前收益(元)</td>\n" +
                        "                 </tr>");
                //填充数据
                StringBuffer funds=new StringBuffer();
                if (finances.getFundFormList()!=null){
                    finances.getFundFormList().forEach(fund->{
                        String fundhtml=" <tr>\n" +
                                "                  <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getFundCode()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getFundName()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getCreatedTime()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getCurrentProfit()+"</td>" +
                                "                 </tr>";
                        funds.append(fundhtml);
                    });
                }
                htmlhead.append(funds);
                htmlhead.append("</tbody></table>");
                htmlhead.append(" <p>&nbsp; &nbsp; &nbsp; \uD83D\uDE0E您到目前为止股票收入情况是：</p>\n" +
                        "                 <table style=\"border-collapse: collapse; width: 100%;\" border=\"1\" data-mce-style=\"border-collapse: collapse; width: 100%;\">\n" +
                        "                 <tbody>\n" +
                        "                 <tr>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">股票代码</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">股票名</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">购买时间</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">当前收益(元)</td>\n" +
                        "                 </tr>");
                StringBuffer stocks=new StringBuffer();
                if (finances.getStockFormList()!=null){
                    finances.getStockFormList().forEach(stock->{
                        String stockhtml=" <tr>\n" +
                                "                  <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getStockCode()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getStockName()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getCreatedTime()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getCurrentProfit()+"</td>" +
                                "                 </tr>";
                        stocks.append(stockhtml);
                    });
                }
                htmlhead.append(stocks);
                htmlhead.append("</tbody></table></body>");
                htmlhead.append(" <div data-row=\"0\" role=\"presentation\" class=\"ephox-snooker-resizer-rows ephox-snooker-resizer-bar\" style=\"position: absolute; left: 16px; top: 373.833px; height: 7px; width: 939px;\"></div>\n" +
                        "                 <div data-row=\"1\" role=\"presentation\" class=\"ephox-snooker-resizer-rows ephox-snooker-resizer-bar\" style=\"position: absolute; left: 16px; top: 409.687px; height: 7px; width: 939px;\"></div>\n" +
                        "                 <div data-column=\"0\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 130.083px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"1\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 247.333px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"2\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 364.583px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"3\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 481.833px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"4\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 599.083px; top: 318.75px; height: 94.7708px; width: 7px;\"></div><div data-column=\"5\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 716.333px; top: 318.75px; height: 94.7708px; width: 7px;\"></div><div data-column=\"6\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 833.583px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"7\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 950.583px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 </html>\n");
                MimeMessagePreparator message= mimeMessage -> {
                    mimeMessage.setRecipient(Message.RecipientType.TO,

                            new InternetAddress(user.getEmail()));//收件人

                    mimeMessage.setFrom(new InternetAddress("572174279@qq.com"));//发件人

                    mimeMessage.setSubject("大吉大利！");//邮件标题

                    mimeMessage.setSentDate(new Date());//发送时间

                    mimeMessage.setContent(htmlhead.toString(),"text/html;charset=utf-8");// 网页格式
                };
                javaMailSender.send(message);
            });
        }

        return "success!";
    }

    @Scheduled(cron = "0 30 22 ? * FRI")  //每年12月2号22点30分00秒触发任务
    @Bean
    public String senderMailWeekly() {

        List<UserQueryForm> userList1= Convert.convert(new TypeReference<List<UserQueryForm>>() {}, service8001.queryUserList1(new UserQueryForm()).getData());
        List<UserQueryForm> userList=userList1.stream().filter(user->user.getEmail()!=null && user.getEmail().length()!=0).collect(Collectors.toList());
        Map<String,List<UserQueryForm>> famEmails=userList.stream().collect(Collectors.groupingBy(UserQueryForm::getFamilyId));

        for(List<UserQueryForm> users:famEmails.values()){
            users.forEach(user->{
                IAEForm iae=new IAEForm();
                iae.setFamId(user.getFamilyId());
                iae.setUserId(user.getUserId());
                iae.setDate("week");

                AcdaForm acad=new AcdaForm();
                acad.setFamId(user.getFamilyId());
                acad.setUserId(user.getUserId());
                acad.setDate("week");

                FundAndStockForm form=new FundAndStockForm();
                form.setUserId(user.getUserId());

                IAEForm iaes= Convert.convert(IAEForm.class,service8002.queryIaeCurrent(iae).getData());
                AcdaForm acadForm= Convert.convert(AcdaForm.class,service8004.queryAcad(acad).getData()) ;
                FundAndStockForm finances=Convert.convert(FundAndStockForm.class,service8005.queryProfits(form).getData());

                StringBuffer htmlhead=new StringBuffer("<!DOCTYPE html>\n" +
                        "                         <html>\n" +
                        "                         <head>\n" +
                        "                         <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "                         <link rel=\"stylesheet\" type=\"text/css\" id=\"u0\" href=\"https://zh.rakko.tools/tools/129/lib/tinymce/skins/ui/oxide/content.min.css\">\n" +
                        "                         <link rel=\"stylesheet\" type=\"text/css\" id=\"u1\" href=\"https://zh.rakko.tools/tools/129/lib/tinymce/skins/content/default/content.min.css\">\n" +
                        "                         </head>\n" +
                        "                         <body id=\"tinymce\" class=\"mce-content-body \" data-id=\"content\" contenteditable=\"true\" spellcheck=\"false\">\n" +
                        "                         <p>您的周报已送达！\uD83D\uDE00</p>"+
                        "                         <p>&nbsp; &nbsp; &nbsp;  \uD83D\uDE06您这周的消费是："+iaes.getExpense()+"元；</p>\n" +
                        "                         <p>&nbsp; &nbsp; &nbsp;  \uD83E\uDD29您这周的收入是："+iaes.getIncome()+"元；</p>\n" +
                        "                         <p>&nbsp; &nbsp; &nbsp;  \uD83E\uDD13与您有关的固定资产有：</p>\n" +
                        "                 <table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n" +
                        "                 <tbody>\n" +
                        "                 <tr>\n" +
                        "                 <td style=\"width: 14.2857%;\">资产名称</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">资产地点</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">购买时间</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">资产分期(0为不分期1为分期)</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">剩余期限/月</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">每期还款金额(元)</td>\n" +
                        "                 <td style=\"width: 14.2857%;\">备注</td></tr>\n");
                StringBuffer assets= new StringBuffer();
                if(acadForm.getAssets()!=null){
                    acadForm.getAssets().forEach(asset->{
                        String assethtml="<tr>\n"+
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsName()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsLocation()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsBuytime()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getAssetsInstalment()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getInstalmentSurplus()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getInstalmentSurplus()+"</td>\n" +
                                "                 <td style=\"width: 14.2857%;\">"+asset.getRemark()+"</td>\n" +
                                "</tr>\n";
                        assets.append(assethtml);
                    });

                }
                htmlhead.append(assets);
                htmlhead.append("</tbody></table>");
                htmlhead.append(" <p>&nbsp; &nbsp; &nbsp;  \uD83E\uDD2D与您有关的借贷有：</p><table style=\"border-collapse: collapse; width: 100%;\" border=\"1\"><tbody><tr><td style=\"width: 12.5%;\">是否完成</td><td style=\"width: 12.5%;\">债权人</td><td style=\"width: 12.5%;\">债务人</td><td style=\"width: 12.5%;\">债权人联系方式</td><td style=\"width: 12.5%;\">债务人联系方式</td><td style=\"width: 12.5%;\">借贷金额</td><td style=\"width: 12.5%;\">借入/借出时间</td><td style=\"width: 12.5%;\">备注</td></tr>");
                StringBuffer cads=new StringBuffer();
                if (acadForm.getCads()!=null){
                    acadForm.getCads().forEach(cad->{
                        String cadhtml=" <tr>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCadStatus()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCreditor()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getObligor()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCreditorTel()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getObligorTel()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCadAmount()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getCadTime()+"</td>\n" +
                                "                 <td style=\"width: 12.5%;\">"+cad.getRemark()+"</td>\n" +
                                "                 </tr>";
                        cads.append(cadhtml);
                    });
                }
                htmlhead.append(cads);
                htmlhead.append("</tbody></table>");
                htmlhead.append(" <p>&nbsp; &nbsp; &nbsp;  \uD83D\uDE18您到目前为止基金收入情况是：</p>\n" +
                        "                 <table style=\"border-collapse: collapse; width: 100%;\" border=\"1\" data-mce-style=\"border-collapse: collapse; width: 100%;\">\n" +
                        "                 <tbody>\n" +
                        "                 <tr>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">基金代码</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">基金名</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">购买时间</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">当前收益(元)</td>\n" +
                        "                 </tr>");
                //填充数据
                StringBuffer funds=new StringBuffer();
                if (finances.getFundFormList()!=null){
                    finances.getFundFormList().forEach(fund->{
                        String fundhtml=" <tr>\n" +
                                "                  <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getFundCode()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getFundName()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getCreatedTime()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+fund.getCurrentProfit()+"</td>" +
                                "                 </tr>";
                        funds.append(fundhtml);
                    });
                }
                htmlhead.append(funds);
                htmlhead.append("</tbody></table>");
                htmlhead.append(" <p>&nbsp; &nbsp; &nbsp; \uD83D\uDE0E您到目前为止股票收入情况是：</p>\n" +
                        "                 <table style=\"border-collapse: collapse; width: 100%;\" border=\"1\" data-mce-style=\"border-collapse: collapse; width: 100%;\">\n" +
                        "                 <tbody>\n" +
                        "                 <tr>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">股票代码</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">股票名</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">购买时间</td>\n" +
                        "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">当前收益(元)</td>\n" +
                        "                 </tr>");
                StringBuffer stocks=new StringBuffer();
                if (finances.getStockFormList()!=null){
                    finances.getStockFormList().forEach(stock->{
                        String stockhtml=" <tr>\n" +
                                "                  <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getStockCode()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getStockName()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getCreatedTime()+"</td>\n" +
                                "                 <td style=\"width: 25%;\" data-mce-style=\"width: 25%;\">"+stock.getCurrentProfit()+"</td>" +
                                "                 </tr>";
                        stocks.append(stockhtml);
                    });
                }
                htmlhead.append(stocks);
                htmlhead.append("</tbody></table></body>");
                htmlhead.append(" <div data-row=\"0\" role=\"presentation\" class=\"ephox-snooker-resizer-rows ephox-snooker-resizer-bar\" style=\"position: absolute; left: 16px; top: 373.833px; height: 7px; width: 939px;\"></div>\n" +
                        "                 <div data-row=\"1\" role=\"presentation\" class=\"ephox-snooker-resizer-rows ephox-snooker-resizer-bar\" style=\"position: absolute; left: 16px; top: 409.687px; height: 7px; width: 939px;\"></div>\n" +
                        "                 <div data-column=\"0\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 130.083px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"1\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 247.333px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"2\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 364.583px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"3\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 481.833px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"4\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 599.083px; top: 318.75px; height: 94.7708px; width: 7px;\"></div><div data-column=\"5\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 716.333px; top: 318.75px; height: 94.7708px; width: 7px;\"></div><div data-column=\"6\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 833.583px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 <div data-column=\"7\" role=\"presentation\" class=\"ephox-snooker-resizer-cols ephox-snooker-resizer-bar\" style=\"position: absolute; left: 950.583px; top: 318.75px; height: 94.7708px; width: 7px;\"></div>\n" +
                        "                 </html>\n");
                MimeMessagePreparator message= mimeMessage -> {
                    mimeMessage.setRecipient(Message.RecipientType.TO,

                            new InternetAddress(user.getEmail()));//收件人

                    mimeMessage.setFrom(new InternetAddress("572174279@qq.com"));//发件人

                    mimeMessage.setSubject("大吉大利！");//邮件标题

                    mimeMessage.setSentDate(new Date());//发送时间

                    mimeMessage.setContent(htmlhead.toString(),"text/html;charset=utf-8");// 网页格式
                };
                javaMailSender.send(message);
            });
        }

        return "success!";
    }

}
