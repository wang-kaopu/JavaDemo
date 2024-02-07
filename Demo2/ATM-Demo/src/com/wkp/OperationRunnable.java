package com.wkp;

import com.wkp.service.ATMImpl;

public class OperationRunnable implements Runnable{
    private ATMImpl atm;
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"正在运行ATM系统");
            atm.start();
            atm.login();
            atm.menu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public OperationRunnable(ATMImpl atm) {
        this.atm = atm;
    }
}
