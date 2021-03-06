package com.tcl.mails.controller;

import com.tcl.mails.service.CodeMailService;
import com.tcl.mails.service.MessageService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {


    @Autowired
    private CodeMailService codeMailService;

    //发送验证码
    @RequestMapping("/sendCode")
    @ResponseBody
    String sendCode(String mail, HttpSession session){
        //得到验证码并将其发送给输入的邮箱
        String code =null;
        if (session.getAttribute("code")==null) {
             code = codeMailService.simpleMail(mail);
            System.out.println(mail +"\t"+code);
        }

        if (null!=mail&&!mail.equals("")){
            //将邮箱和验证码保存到session中以便验证
            session.setAttribute("mail",mail);
            if (session.getAttribute("code")==null){
                session.setAttribute("code",code);
            }
            return "发送成功";
        }else {
            return "发送失败";
        }
    }

    @GetMapping("/login")
    String goLogin(){
        return "login";
    }


    @PostMapping("/login")
    String login(String mail,String code){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(mail,code);
        try{
            subject.login(token);
            subject.getSession().removeAttribute("code");
        }catch (AuthenticationException ae){
            return "login";
        }
     return "redirect:admin/index";
    }
}
