package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Maintain")
public class Maintain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*id自增*/
    private Integer id;
    @Column(name = "account")
    private String account;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "datetime")
    private Date datetime;

    @Column(name = "description")
    private String description;
    @Column(name = "state")
    private String state;
    @Column(name = "heating_account")
    private String heating_account;
    @Column(name = "heating_name")
    private String heating_name;


    public Maintain() {
    }

    public Maintain(Integer id, String account, String name, String address, Date datetime, String description, String state, String heating_account, String heating_name) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.address = address;
        this.datetime = datetime;
        this.description = description;
        this.state = state;
        this.heating_account = heating_account;
        this.heating_name = heating_name;
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
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return heating_account
     */
    public String getHeating_account() {
        return heating_account;
    }

    /**
     * 设置
     * @param heating_account
     */
    public void setHeating_account(String heating_account) {
        this.heating_account = heating_account;
    }

    /**
     * 获取
     * @return heating_name
     */
    public String getHeating_name() {
        return heating_name;
    }

    /**
     * 设置
     * @param heating_name
     */
    public void setHeating_name(String heating_name) {
        this.heating_name = heating_name;
    }

    public String toString() {
        return "Maintain{id = " + id + ", account = " + account + ", name = " + name + ", address = " + address + ", datetime = " + datetime + ", description = " + description + ", state = " + state + ", heating_account = " + heating_account + ", heating_name = " + heating_name + "}";
    }
}
