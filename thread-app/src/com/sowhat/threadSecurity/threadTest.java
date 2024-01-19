package com.sowhat.threadSecurity;

public class threadTest {
    public static void main(String[] args) {
        //1.创建一个账户对象供测试使用
        Account acc = new Account(100000);

        //2.创建两个线程同时对账户对象进行修改操作
        new drawThread("小明", acc, 100000).start();
        new drawThread("小红", acc, 100000).start();
    }
}
