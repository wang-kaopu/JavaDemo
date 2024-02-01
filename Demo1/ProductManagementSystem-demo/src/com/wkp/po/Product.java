package com.wkp.po;

import com.wkp.service.Type;

import java.time.LocalDateTime;

public class Product {
    //商品实体类
    private String name;//名称
    private String id;//编号
    private Type type;//类型
    private double price;//价格
    private int quantity;//库存数量
    private int maxQuatity;//最大库存数量
    private LocalDateTime renewTime;//更新时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMaxQuatity() {
        return maxQuatity;
    }

    public void setMaxQuatity(int maxQuatity) {
        this.maxQuatity = maxQuatity;
    }

    public LocalDateTime getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(LocalDateTime renewTime) {
        this.renewTime = renewTime;
    }

    public Product(String name, String id, Type type, double price, int quantity, int maxQuatity, LocalDateTime renewTime) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.maxQuatity = maxQuatity;
        this.renewTime = renewTime;
    }

    public Product(String name, Type type, double price, int quantity, int maxQuatity, LocalDateTime renewTime) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.maxQuatity = maxQuatity;
        this.renewTime = renewTime;
    }
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", maxQuatity=" + maxQuatity +
                ", renewTime=" + renewTime +
                '}';
    }
}
