package com.tcl.mails.controller;

import com.tcl.mails.entity.Mail;
import com.tcl.mails.service.MailService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/administer")
public class AdministerController {

    @Autowired
    MailService mailService;

    //跳转到管理员界面
    @RequestMapping("users")
    @RequiresRoles("admin")
    public String user(){
      return "admin/administer/users";
    }

    //查看所有状态相同的用户（1位白名单，0为黑名单）
    @RequestMapping("showUsers")
    @ResponseBody
    @RequiresRoles("admin")
    public List<Mail> showUser(int state){
        return mailService.findUserByState(state);
    }

    //修改用户的状态
    @RequestMapping("updateState")
    @RequiresRoles("admin")
    @ResponseBody
    public String updateState(int id,int state){
        mailService.updateState(id, state);
        return "success";
    }
}
