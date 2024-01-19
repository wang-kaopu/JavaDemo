package com.sowhat.threadSecurity;

public class Account {
    private double money;

    public double getMoney() {
        return money;
    }

    public void drawMoney(double money){
        //1.获得线程名
        String name = Thread.currentThread().getName();
        //2.判断余额是否足够
        if(this.money >= money){
            System.out.println(name + "来取钱");
            this.money -= money;
            System.out.println(name + "取钱后的余额是" + this.money);
        }else{
            System.out.println(name+"取钱，余额不足");
        }
    }
    public void setMoney(double money) {
        this.money = money;
    }

    public Account() {
    }
    public Account(double money) {
        this.money = money;
    }
}
