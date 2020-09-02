package com.tcl.mails.entity;

import javax.persistence.*;

@Entity
@Table(name = "mail")
public class Mail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String mail;
    @Column
    private int state;

    public Mail() {
    }

    public Mail(String mail) {
        this.mail = mail;
        this.state = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", state=" + state +
                '}';
    }

    public void setState(int state) {
        this.state = state;
    }
}
