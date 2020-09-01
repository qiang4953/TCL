package com.tcl.mails.dao;

import com.tcl.mails.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessageDao extends JpaRepository<Message,Object> {

    //查询收到邮件的状态
    List<Message> findByReceiveMailAndReceiveState(String mail,int state);
    //查询已发送的邮件
    List<Message> findBySendMailAndSendState(String mail,int state);
    //删除邮件
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from message  where send_state=:send and receive_state=:receive")
    void delete(@Param("send") int send, @Param("receive") int receive);
}
