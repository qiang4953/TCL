package com.tcl.mails.service;

import com.tcl.mails.entity.Message;

import java.util.List;

public interface MessageService {

    //发送验证码
    String sendCode(String mail);
    //发送邮件
    Message sendMessage(String receiveMail, String title, String message);
    //查询收件箱
    List<Message> receiveMessage(String mail);
    //查询垃圾箱
    List<Message> dustbin(String mail);
    //查看已发送的邮件
    List<Message> findSendMessage(String mail);
}
