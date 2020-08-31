package com.tcl.mails.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件
 */
public class SendEmail {

    public static String domail(String receiveMail, String title, String text) {
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
            //设置发件人的邮箱地址
            String mymail =  "846678875@qq.com";
            String mymima = "nkbodotapimubccf";//第三方验证
            message.setFrom(new InternetAddress(mymail));
            //设置收件人的邮箱地址
            message.setRecipients(Message.RecipientType.TO, receiveMail);
            //设置邮箱标题
            message.setSubject(title);
            //设置邮件内容
            message.setText(text);
            //获取邮差对象
            Transport transport = session.getTransport();
            //连接发件人的邮箱账户
            transport.connect(mymail, mymima); //自定义
            message.setContent(text,"text/html;charset=GBK");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return "success";
        }catch (Exception e){
            return "fail";
        }
    }
}
