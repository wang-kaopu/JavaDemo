package com.wkp;

import com.wkp.service.ATMImpl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test{
    public static void main(String[] args) throws Exception {
        ATMImpl atm = new ATMImpl();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 8,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        poolExecutor.execute(new OperationRunnable(atm));
        //new Thread(new OperationRunnable(atm)).start();
        poolExecutor.execute(new OperationRunnable(atm));
    }
}
