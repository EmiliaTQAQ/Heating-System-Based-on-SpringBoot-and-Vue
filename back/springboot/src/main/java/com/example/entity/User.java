package com.example.entity;

import javax.persistence.*;

@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*id自增*/
    private Integer id;
    @Column(name = "img")
    private String img;
    @Column(name = "account")
    private String account;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "living_space")
    private Double living_space;
    @Column(name = "sex")
    private String sex;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    private Integer age;
    @Column(name = "role")
    private String role;
    @Column(name = "cell")
    private String cell;
    @Column(name = "building")
    private String building;
    @Column(name = "gate")
    private String gate;
    @Transient
    private String token;
    @Transient
    private String verCode;


    public User() {
    }

    public User(Integer id, String img, String account, String name, String password, String address, Double balance, Double living_space, String sex, String phone, Integer age, String role, String cell, String building, String gate, String token, String verCode) {
        this.id = id;
        this.img = img;
        this.account = account;
        this.name = name;
        this.password = password;
        this.address = address;
        this.balance = balance;
        this.living_space = living_space;
        this.sex = sex;
        this.phone = phone;
        this.age = age;
        this.role = role;
        this.cell = cell;
        this.building = building;
        this.gate = gate;
        this.token = token;
        this.verCode = verCode;
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
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
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
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置
     * @param balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
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

    /**
     * 获取
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 获取
     * @return cell
     */
    public String getCell() {
        return cell;
    }

    /**
     * 设置
     * @param cell
     */
    public void setCell(String cell) {
        this.cell = cell;
    }

    /**
     * 获取
     * @return building
     */
    public String getBuilding() {
        return building;
    }

    /**
     * 设置
     * @param building
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * 获取
     * @return gate
     */
    public String getGate() {
        return gate;
    }

    /**
     * 设置
     * @param gate
     */
    public void setGate(String gate) {
        this.gate = gate;
    }

    /**
     * 获取
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取
     * @return verCode
     */
    public String getVerCode() {
        return verCode;
    }

    /**
     * 设置
     * @param verCode
     */
    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String toString() {
        return "User{id = " + id + ", img = " + img + ", account = " + account + ", name = " + name + ", password = " + password + ", address = " + address + ", balance = " + balance + ", living_space = " + living_space + ", sex = " + sex + ", phone = " + phone + ", age = " + age + ", role = " + role + ", cell = " + cell + ", building = " + building + ", gate = " + gate + ", token = " + token + ", verCode = " + verCode + "}";
    }
}
