package com.wkp;

import com.wkp.service.ATMImpl;

public class Test{
    public static void main(String[] args) throws Exception {
        ATMImpl atm = new ATMImpl();
        atm.start();
        atm.login();
        atm.menu();
    }
}
