package com.wkp;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        ThreadConnection threadConnection = new ThreadConnection();
        for(int i = 1; i<=10; i++){
            Thread thread = new Thread(threadConnection, "线程：" + i);
            thread.start();
        }
    }

    static class ThreadConnection implements Runnable{
        @Override
        public void run() {
            Connection connection = ConnectionPoolManager.getConnection();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ConnectionPoolManager.releaseConnection(connection);
        }
    }
}
