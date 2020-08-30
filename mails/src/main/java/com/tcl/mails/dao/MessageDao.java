package com.tcl.mails.dao;

import com.tcl.mails.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends JpaRepository<Message,Object> {

    //查询收件箱
    List<Message> findByReceiveMailAndReceiveStateNot(String mail,int state);
    //查询垃圾箱
    List<Message> findByReceiveMailAndReceiveState(String mail,int state);
    //查询已发送的邮件
    List<Message> findBySendMailAndSendState(String mail,int state);
}
