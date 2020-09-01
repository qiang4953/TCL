package com.tcl.mails.service;

import com.tcl.mails.entity.Mail;

import java.util.List;

public interface MailService {
    //查看所有用户
    List<Mail> findUserAll();
    //查询单个用户
    List<Mail> findUserByState(int state);
    //修改用户状态
    Mail updateState(int id,int state);

}
