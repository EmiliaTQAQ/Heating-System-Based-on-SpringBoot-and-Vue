package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*id自增*/
    private Integer id;
    @Column(name = "content")
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "datetime")
    private Date datetime;
    @Column(name = "state")
    private String state;


    public Notice() {
    }

    public Notice(Integer id, String content, Date datetime, String state) {
        this.id = id;
        this.content = content;
        this.datetime = datetime;
        this.state = state;
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

    public String toString() {
        return "Notice{id = " + id + ", content = " + content + ", datetime = " + datetime + ", state = " + state + "}";
    }
}
