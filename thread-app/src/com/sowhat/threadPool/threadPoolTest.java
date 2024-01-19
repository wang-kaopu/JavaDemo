package com.sowhat.threadPool;

import java.util.concurrent.*;

public class threadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        public ThreadPoolExecutor(int corePoolSize,
//        int maximumPoolSize,
//        long keepAliveTime,
//        TimeUnit unit,
//        BlockingQueue<Runnable> workQueue,
//        ThreadFactory threadFactory,
//        RejectedExecutionHandler handler) {
            ExecutorService pool = new ThreadPoolExecutor(3, 5, 8,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(4),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());
        Runnable target = new MyRunnable();
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);

        Future<String> s1 = pool.submit(new MyCallable());
        System.out.println(s1.get());

        Future<String> s2 = pool.submit(new MyCallable());
        System.out.println(s2.get());

        Future<String> s3 = pool.submit(new MyCallable());
        System.out.println(s3.get());

        Future<String> s4 = pool.submit(new MyCallable());
        System.out.println(s4.get());

        Future<String> s5 = pool.submit(new MyCallable());
        System.out.println(s5.get());

    }
}
