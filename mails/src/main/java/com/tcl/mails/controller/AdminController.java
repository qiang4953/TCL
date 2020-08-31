package com.tcl.mails.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //跳转到主页
    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }
    //跳转到发邮件
    @RequestMapping("/sendMessage")
    public String sendMessage(){
        return "admin/user/sendMessage";
    }
    //跳转到发件箱
    @RequestMapping("/findSendMessage")
    public String findSendMessage(){
        return "admin/user/findSendMessage";
    }
    //跳转到收件箱
    @RequestMapping("/receiveMessage")
    public String receiveMessage(){
        return "admin/user/receiveMessage";
    }
    //跳转到垃圾箱
    @RequestMapping("/dustbin")
    public String dustbin(){
        return "admin/user/dustbin";
    }
    //跳转主页
    @RequestMapping("/info")
    public String info(){
        return "/admin/user/info";
    }
    //跳转登录页
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
