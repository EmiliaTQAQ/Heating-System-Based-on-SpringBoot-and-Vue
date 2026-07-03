package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*id自增*/
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "time")
    private String time;
    @Column(name = "ip")
    private String ip;


    public Log() {
    }

    public Log(Integer id, String name, String username, String time, String ip) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.time = time;
        this.ip = ip;
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String toString() {
        return "Log{id = " + id + ", name = " + name + ", username = " + username + ", time = " + time + ", ip = " + ip + "}";
    }
}
