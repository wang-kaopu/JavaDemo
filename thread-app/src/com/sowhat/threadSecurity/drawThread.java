package com.sowhat.threadSecurity;

public class drawThread extends Thread {
    private Account acc;
    private double money;
    public drawThread(String name, Account acc, double money) {
        super(name);
        this.acc = acc;
        this.money = money;
    }
    @Override
    public void run() {
        acc.drawMoney(100000);
    }
}
