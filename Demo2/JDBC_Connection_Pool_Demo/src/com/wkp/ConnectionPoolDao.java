package com.wkp;

import java.sql.Connection;

public interface ConnectionPoolDao {
    //获取连接
    public Connection creatConn();

    //释放连接
    public void releaseConnection(Connection conn);
}
