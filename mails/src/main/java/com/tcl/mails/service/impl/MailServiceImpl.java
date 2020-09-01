package com.tcl.mails.service.impl;

import com.tcl.mails.dao.MailDao;
import com.tcl.mails.entity.Mail;
import com.tcl.mails.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    MailDao mailDao;

    /**
     * 查询所有的用户
     * @return
     */
    @Override
    public List<Mail> findUserAll() {
        return mailDao.findByIdNot(1);
    }

    /**
     * 查询状态的的邮箱（0为黑名单，1位白名单）
     * @param state
     * @return
     */
    @Override
    public List<Mail> findUserByState(int state) {
        List<Mail> list = mailDao.findByStateAndIdNot(state,1);
        return list;
    }

    @Override
    public Mail updateState(int id, int state) {
        Mail mail = mailDao.getOne(id);
        mail.setState(state);
        return mailDao.save(mail);
    }
}
