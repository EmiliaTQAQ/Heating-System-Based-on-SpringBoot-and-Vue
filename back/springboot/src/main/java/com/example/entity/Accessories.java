package com.example.entity;

import javax.persistence.*;

@Table(name = "accessories")
public class Accessories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "accessories")
    private String accessories;
    @Column(name = "inventory")
    private Integer inventory;

    public Accessories() {
    }

    public Accessories(Integer id, String accessories, Integer inventory) {
        this.id = id;
        this.accessories = accessories;
        this.inventory = inventory;
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
     * @return inventory
     */
    public Integer getInventory() {
        return inventory;
    }

    /**
     * 设置
     * @param inventory
     */
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String toString() {
        return "Accessories{id = " + id + ", accessories = " + accessories + ", inventory = " + inventory + "}";
    }
}
