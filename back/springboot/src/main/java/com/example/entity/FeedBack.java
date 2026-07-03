package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "feedback")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*id自增*/
    private Integer id;
    @Column(name = "content")
    private String content;
    @Column(name = "account")
    private String account;
    @Column(name = "name")
    private String name;
    @Column(name = "state")
    private String state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "datetime")
    private Date datetime;


    public FeedBack() {
    }

    public FeedBack(Integer id, String content, String account, String name, String state, Date datetime) {
        this.id = id;
        this.content = content;
        this.account = account;
        this.name = name;
        this.state = state;
        this.datetime = datetime;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * 设置
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取
     * @return datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * 设置
     * @param datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String toString() {
        return "FeedBack{id = " + id + ", content = " + content + ", account = " + account + ", name = " + name + ", state = " + state + ", datetime = " + datetime + "}";
    }
}
