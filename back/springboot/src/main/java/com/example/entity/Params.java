package com.example.entity;

public class Params {
    private String account;
    private String name;
    private String phone;
    private String accessories;
    private String state;

    private String heating_account;
    private Integer pageNum;
    private Integer pageSize;


    public Params() {
    }

    public Params(String account, String name, String phone, String accessories, String state, String heating_account, Integer pageNum, Integer pageSize) {
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.accessories = accessories;
        this.state = state;
        this.heating_account = heating_account;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
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
     * @return accessories
     */
    public String getAccessories() {
        return accessories;
    }

    /**
     * 设置
     * @param accessories
     */
    public void setAccessories(String accessories) {
        this.accessories = accessories;
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
     * @return pageNum
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * 设置
     * @param pageNum
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 获取
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return "Params{account = " + account + ", name = " + name + ", phone = " + phone + ", accessories = " + accessories + ", state = " + state + ", heating_account = " + heating_account + ", pageNum = " + pageNum + ", pageSize = " + pageSize + "}";
    }
}
