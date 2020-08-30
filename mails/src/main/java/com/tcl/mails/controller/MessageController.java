package com.tcl.mails.controller;

import com.tcl.mails.entity.Message;
import com.tcl.mails.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("message")
public class MessageController {

    @Autowired
    MessageService service;

    //发送邮件
    @RequestMapping("sendMessage")
    @ResponseBody
    String sendMessage(String receiveMail, String title, String message, HttpServletRequest request){
        Message s =  service.sendMessage(receiveMail,title,message);
        if (null!=s){
            return "发送成功<br><a href='/admin/index'>返回主页</a>";
        }else {
            return "发送失败<br><a href='/admin/index'>返回主页</a>";
        }
    }

    //查看接收到的邮件
    @RequestMapping("receiveMessage")
    @ResponseBody
    List<Message> receiveMessage(HttpSession session){
        String mail = (String) session.getAttribute("mail");
        List<Message> list = service.receiveMessage(mail);
        return list;
    }
    //查看垃圾邮件
    @RequestMapping("dustbin")
    @ResponseBody
    List<Message> dustbin(HttpSession session){
        String mail = (String) session.getAttribute("mail");
        List<Message> list = service.dustbin(mail);
        return list;
    }
    //查看已发送的邮件
    @RequestMapping("findSendMessage")
    @ResponseBody
    List<Message> findSendMessage(HttpSession session){
        String mail = (String) session.getAttribute("mail");
        System.out.println(mail);
        List<Message> list = service.findSendMessage(mail);
        System.out.println(list);
        return list;
    }
}
