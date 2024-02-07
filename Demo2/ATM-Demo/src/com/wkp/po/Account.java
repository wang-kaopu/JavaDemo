package com.wkp.po;

import java.math.BigDecimal;

public class Account{
    private String name;
    private String phoneNumber;
    private String identityId;
    private String cardId;
    private BigDecimal money;
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNUmber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", phoneNUmber='" + phoneNumber + '\'' +
                ", identityId='" + identityId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", money=" + money +
                ", key='" + key + '\'' +
                '}';
    }

    public Account(String name, String phoneNumber, String identityId, String cardId, BigDecimal money, String key) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.identityId = identityId;
        this.cardId = cardId;
        this.money = money;
        this.key = key;
    }


}
