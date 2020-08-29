package com.tcl.mails.dao;

import com.tcl.mails.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailDao extends JpaRepository<Mail,Object> {
        Mail findByMail(String mail);
}
