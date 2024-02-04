package com.wkp;

import java.sql.Connection;

import javax.xml.crypto.Data;


public class ConnectionPoolManager {
    private static DataSourceConfig config = new DataSourceConfig();
    private static ConnectionPool connectionPool = new ConnectionPool(config);

    public static Connection getConnection(){
        return connectionPool.getConn().getConn();
    }

    public static void releaseConnection(Connection connection){
        connectionPool.releaseConnection(connection);
    }

}
