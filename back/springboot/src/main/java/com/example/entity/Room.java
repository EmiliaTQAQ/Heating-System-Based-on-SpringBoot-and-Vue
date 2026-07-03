package com.example.entity;

import cn.hutool.core.annotation.Alias;

import javax.persistence.*;

@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*id自增*/
    private Integer id;
    @Alias("小区")/*Alias映射到表使得导入Excel表格的表头能和数据库数据一一对应*/
    @Column(name = "cell")
    private String cell;
    @Alias("楼")
    @Column(name = "building")
    private String building;
    @Alias("门牌号")
    @Column(name = "gate")
    private String gate;
    @Alias("状态")
    @Column(name = "state")
    private String state;
    @Column(name = "heating_account")
    private String heating_account;
    @Column(name = "heating_name")
    private String heating_name;


    public Room() {
    }

    public Room(Integer id, String cell, String building, String gate, String state, String heating_account, String heating_name) {
        this.id = id;
        this.cell = cell;
        this.building = building;
        this.gate = gate;
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
        return "Room{id = " + id + ", cell = " + cell + ", building = " + building + ", gate = " + gate + ", state = " + state + ", heating_account = " + heating_account + ", heating_name = " + heating_name + "}";
    }
}
