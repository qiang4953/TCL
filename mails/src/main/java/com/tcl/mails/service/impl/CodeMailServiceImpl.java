package com.tcl.mails.service.impl;

import com.tcl.mails.service.CodeMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CodeMailServiceImpl implements CodeMailService {
    @Autowired
    private JavaMailSender jms;
    //邮件发送者的账号
    @Value("spring.mail.username")
    private String from;

    @Override
    public String simpleMail(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("846678875@qq.com");
        message.setTo(mail);
        message.setSubject("验证码");
        //随机生成6位验证码
        int s = (int) (Math.random() * 1000000);
        //将s转为6位的字符串，如果不足左边补0
        String code = String.format("%06d", s);
        message.setText(code);
        jms.send(message);
        return code;
    }
}
