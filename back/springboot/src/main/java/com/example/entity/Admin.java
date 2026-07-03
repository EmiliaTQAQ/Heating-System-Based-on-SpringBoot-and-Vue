package com.example.entity;

import javax.persistence.*;

@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*id自增*/
    private Integer id;
    @Column(name = "account")/*admin里面的字段名和数据库里面的字段名对应*/
    private String account;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Transient
    private String token;

    public Admin() {
    }

    public Admin(Integer id, String account, String password, String role, String token) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.role = role;
        this.token = token;
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

    public String toString() {
        return "Admin{id = " + id + ", account = " + account + ", password = " + password + ", role = " + role + ", token = " + token + "}";
    }
}
