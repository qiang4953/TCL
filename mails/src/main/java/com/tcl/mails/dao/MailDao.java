package com.tcl.mails.dao;

import com.tcl.mails.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailDao extends JpaRepository<Mail,Object> {
        //通过邮箱查找
        Mail findByMail(String mail);
        //查看所有非管理员
        List<Mail> findByIdNot(int id);
        //查看所有状态一样的非管理员
        List<Mail> findByStateAndIdNot(int state,int id);
}
