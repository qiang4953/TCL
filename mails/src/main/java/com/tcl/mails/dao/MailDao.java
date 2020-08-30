package com.tcl.mails.dao;

import com.tcl.mails.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailDao extends JpaRepository<Mail,Object> {
        //通过邮箱查找，用于判断是否存在数据库中
        Mail findByMail(String mail);
}
