package com.example.entity;

import javax.persistence.*;

@Table(name = "heating_engineer")
public class Heating_engineer {
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
    @Column(name = "salary")
    private Double salary;
    @Column(name = "sex")
    private String sex;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    private Integer age;
    @Column(name = "role")
    private String role;
    @Transient
    private String token;
    @Transient
    private String verCode;


    public Heating_engineer() {
    }

    public Heating_engineer(Integer id, String img, String account, String name, String password, Double salary, String sex, String phone, Integer age, String role, String token, String verCode) {
        this.id = id;
        this.img = img;
        this.account = account;
        this.name = name;
        this.password = password;
        this.salary = salary;
        this.sex = sex;
        this.phone = phone;
        this.age = age;
        this.role = role;
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
     * @return salary
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * 设置
     * @param salary
     */
    public void setSalary(Double salary) {
        this.salary = salary;
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
        return "Heating_engineer{id = " + id + ", img = " + img + ", account = " + account + ", name = " + name + ", password = " + password + ", salary = " + salary + ", sex = " + sex + ", phone = " + phone + ", age = " + age + ", role = " + role + ", token = " + token + ", verCode = " + verCode + "}";
    }
}
