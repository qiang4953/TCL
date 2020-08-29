package com.tcl.mails.service.impl;

import com.tcl.mails.dao.MessageDao;
import com.tcl.mails.entity.Message;
import com.tcl.mails.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    //生成验证码用于登录或注册
    @Override
    public String sendCode(String mail) {

        //随机生成6位验证码
        int s = (int)(Math.random()*1000000);
        //将s转为6位的字符串，如果不足左边补0
        String code = String.format("%06d", s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss");
        String date = simpleDateFormat.format(new Date());
        //将验证码和邮箱保存到数据库中
        Message codeMessage = new Message("admin",mail,"验证码",code,date);
        messageDao.save(codeMessage);

       //将验证码发送达到网络邮箱中



        return code;
    }
}
