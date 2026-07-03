package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "advance_fee")
public class Advance_fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*id自增*/
    private Integer id;
    @Column(name = "account")
    private String account;
    @Column(name = "name")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "datetime")
    private Date datetime;
    @Column(name = "paymentAmount")
    private String paymentAmount;
    @Column(name = "paymentCycle")
    private String paymentCycle;
    @Column(name = "address")
    private String address;
    @Column(name = "living_space")
    private Double living_space;


    public Advance_fee() {
    }

    public Advance_fee(Integer id, String account, String name, Date datetime, String paymentAmount, String paymentCycle, String address, Double living_space) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.datetime = datetime;
        this.paymentAmount = paymentAmount;
        this.paymentCycle = paymentCycle;
        this.address = address;
        this.living_space = living_space;
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
     * @return paymentAmount
     */
    public String getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * 设置
     * @param paymentAmount
     */
    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * 获取
     * @return paymentCycle
     */
    public String getPaymentCycle() {
        return paymentCycle;
    }

    /**
     * 设置
     * @param paymentCycle
     */
    public void setPaymentCycle(String paymentCycle) {
        this.paymentCycle = paymentCycle;
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
     * @return living_space
     */
    public Double getLiving_space() {
        return living_space;
    }

    /**
     * 设置
     * @param living_space
     */
    public void setLiving_space(Double living_space) {
        this.living_space = living_space;
    }

    public String toString() {
        return "Pay_the_fees{id = " + id + ", account = " + account + ", name = " + name + ", datetime = " + datetime + ", paymentAmount = " + paymentAmount + ", paymentCycle = " + paymentCycle + ", address = " + address + ", living_space = " + living_space + "}";
    }
}
