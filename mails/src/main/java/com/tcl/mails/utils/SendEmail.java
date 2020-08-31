package com.tcl.mails.utils;

//import com.ssm.resultInfo.ResultInfo;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;


/**
 * 发送邮件
 */
public class SendEmail {

    public static String domail(String myMail, String code, String sendToMail, String title, String content) {
        System.out.println(myMail);
        try {
            Properties properties = new Properties();
            properties.put("mail.transport.protocol", "smtp");// 连接协议
            properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
            properties.put("mail.smtp.port", 465);// 端口号
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
            properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
            //得到会话对象
            Session session = Session.getInstance(properties);
            //获取邮件对象
            MimeMessage message = new MimeMessage(session);
            Scanner s = new Scanner(System.in);
            //设置发件人的邮箱地址
            System.out.print("输入你发邮件所用的邮箱：");
            String mymail =  myMail;
            System.out.print("输入你邮箱的第三方验证码：");
            String mymima = code;
            message.setFrom(new InternetAddress(mymail));
            //设置收件人的邮箱地址

            System.out.print("输入收件人邮箱：");
            String shoujianren = sendToMail;
            message.setRecipients(Message.RecipientType.TO, shoujianren);

            //设置邮箱标题

            System.out.print("输入标题：");
            String subject = title;
            message.setSubject(subject);

            //设置邮件内容
            System.out.print("输入内容：");
            String text = content;
            message.setText(text);
            //获取邮差对象
            Transport transport = session.getTransport();
            //连接发件人的邮箱账户
            transport.connect(mymail, mymima); //自定义
            message.setContent(content,"text/html;charset=GBK");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return "success";
        }catch (Exception e){
            return "fail";
        }
    }
}
