package com.tcl.mails.dao;

import com.tcl.mails.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends JpaRepository<Message,Object> {
}
