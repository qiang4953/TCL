package com.tcl.mails.entity;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String sendMail;
    @Column
    private String receiveMail;
    @Column
    private String title;
    @Column
    private String text;
    @Column(name = "send_date")
    private String date;
    @Column
    private int sendState;
    @Column
    private int receiveState;

    public Message() {

    }

    public Message(String sendMail, String receiveMail, String title, String text,String date) {
        this.sendMail = sendMail;
        this.receiveMail = receiveMail;
        this.title = title;
        this.text = text;
        this.date = date;
        this.sendState = 1;
        this.receiveState = 0;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSendMail() {
        return sendMail;
    }

    public void setSendMail(String sendMail) {
        this.sendMail = sendMail;
    }

    public String getReceiveMail() {
        return receiveMail;
    }

    public void setReceiveMail(String receiveMail) {
        this.receiveMail = receiveMail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSendState() {
        return sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

    public int getReceiveState() {
        return receiveState;
    }

    public void setReceiveState(int receiveState) {
        this.receiveState = receiveState;
    }
}
