package com.tcl.mails.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administer")
public class AdministerController {

    @RequestMapping("users")
    @RequiresRoles("admin")
    public String user(){
      return "admin/administer/users";
    }
}
