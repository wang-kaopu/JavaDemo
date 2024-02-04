package com.wkp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool implements ConnectionPoolDao{

    //加载配置类
    DataSourceConfig config;

    //参数，表示当前有多少个活跃的连接
    private AtomicInteger currentActive = new AtomicInteger(0);

    //创建集合存放空闲连接
    Vector<Connection> freePools = new Vector<>();

    //正在使用的连接池
    Vector<PoolEntry> usingPools = new Vector<>();

    //构造器初始化
    public ConnectionPool(DataSourceConfig config){
        this.config = config;
        init();
    }
    //初始化方法
    public void init(){
        try {
            Class.forName(config.getDriver());
            for(int i = 0; i < Integer.valueOf(config.getInit_size()); i++){
                Connection conn = creatConn();
                freePools.add(conn);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //创建链接
    @Override
    public Connection creatConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(config.getUrl(),config.getUser_name(),config.getPassword());
            currentActive.incrementAndGet();
            System.out.println("创建一个新连接"+conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    //获取连接
    public synchronized PoolEntry getConn(){
        Connection conn = null;
        if(!freePools.isEmpty()) {
            conn = freePools.get(0);
            freePools.remove(0);
        }else {
            if(currentActive.get()<Integer.valueOf(config.getMax_size())){
                conn = creatConn();
            }else{
                try {
                    System.out.println(Thread.currentThread().getName()+",连接池最大数量是："+config.getMax_size()+"已经满了，需要等待。");
                    wait(1000);
                    return getConn();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        PoolEntry poolEntry = new PoolEntry(conn, System.currentTimeMillis());
        usingPools.add(poolEntry);
        System.out.println(Thread.currentThread().getName()+",获取并使用连接"+conn+",空闲线程数："+freePools.size()+","+
                "正在使用的线程数："+usingPools.size()+",总线程数："+currentActive.get());
        return poolEntry;
    }
    @Override
    public synchronized void releaseConnection(Connection conn) {
        try {
            if(!conn.isClosed() && conn != null){
                freePools.add(conn);
            }
            for(int i = 0; i < usingPools.size(); i++){
                if(usingPools.get(i).getConn()==conn){
                    usingPools.remove(i);
                }
            }
            System.out.println("回收了一个连接"+conn+",空闲连接数为："+freePools.size()+",正在使用的线程数："+usingPools.size()+",总线程数："+currentActive.get());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public DataSourceConfig getConfig() {
        return config;
    }

    public void setConfig(DataSourceConfig config) {
        this.config = config;
    }

    public AtomicInteger getCurrentActive() {
        return currentActive;
    }

    public void setCurrentActive(AtomicInteger currentActive) {
        this.currentActive = currentActive;
    }

    public Vector<Connection> getFreePools() {
        return freePools;
    }

    public void setFreePools(Vector<Connection> freePools) {
        this.freePools = freePools;
    }

    public Vector<PoolEntry> getUsingPools() {
        return usingPools;
    }

    public void setUsingPools(Vector<PoolEntry> usingPools) {
        this.usingPools = usingPools;
    }
}
