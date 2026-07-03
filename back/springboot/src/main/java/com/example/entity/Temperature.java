package com.example.entity;

import javax.persistence.*;

@Table(name = "temperature")
public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*id自增*/
    private Integer id;
    @Column(name = "account")
    private String account;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "temperature")
    private String temperature;


    public Temperature() {
    }

    public Temperature(Integer id, String account, String name, String address, String temperature) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.address = address;
        this.temperature = temperature;
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
     * @return temperature
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * 设置
     * @param temperature
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String toString() {
        return "Temperature{id = " + id + ", account = " + account + ", name = " + name + ", address = " + address + ", temperature = " + temperature + "}";
    }
}
