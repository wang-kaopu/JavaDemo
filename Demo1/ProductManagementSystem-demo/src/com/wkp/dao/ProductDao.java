package com.wkp.dao;

import com.wkp.po.Product;
import com.wkp.service.InputException;
import com.wkp.service.ProductNotFoundException;
import com.wkp.service.Type;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public interface ProductDao {
    //商品方法接口

    /*
    连接到数据库需要的常量字符串
     */
    String JDBC_URL = "jdbc:mysql://localhost:3306/product_manager_system_database";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "meiyoumima123";

    /*
    增加方法
     */
    static boolean Add(Product p) throws Exception{
        //注册域名
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接
        String sql = "INSERT INTO products (name, type, price, quantity, max_quantity, datetime) values (?,?,?,?,?,?)";
        try(Connection con = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)){
            //准备陈述
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setObject(1,p.getName());
                ps.setObject(2,p.getType().name());
                ps.setObject(3,p.getPrice());
                ps.setObject(4,p.getQuantity());
                ps.setObject(5,p.getMaxQuatity());
                ps.setObject(6,p.getRenewTime());
                return ps.executeUpdate() > 0;
            }
        }
    }

    /*
    删除方法
     */
    static boolean Del(String id) throws Exception {
        //注册域名
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接
        String delsql = "delete from products where id = ?;";
        System.out.println("你要删除的商品是：");
        Select(id);
        try(Connection con = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)){
            //准备陈述
            try(PreparedStatement ps = con.prepareStatement(delsql)){
                ps.setObject(1,id);
                return ps.executeUpdate()>0;
            }
        }
    }

    /*
    修改方法
     */
    static boolean Modify(String id, String gotName, Type gotType, double gotPrice, int gotQuantity, int gotMaxQuantity, LocalDateTime gotDateTime) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接
        String sql = "update products set name = ?, type = ?, price = ?, quantity = ?, max_quantity = ?, datetime = ? where id = ?;" ;
        System.out.println("你要修改的商品是：");
        Select(id);
        try(Connection con = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)){
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setObject(1,gotName);
                ps.setObject(2,gotType.name());
                ps.setObject(3,gotPrice);
                ps.setObject(4,gotQuantity);
                ps.setObject(5,gotMaxQuantity);
                ps.setObject(6,gotDateTime);
                ps.setObject(7,id);

                return ps.executeUpdate()>0;
            }
        }
    }

    /*
    筛选方法
     */
    static void Select(String id) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String selectSql = "select * from products where id = ?;";
        try(Connection con = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            try (PreparedStatement ps = con.prepareStatement(selectSql)) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String gotName = rs.getString("name");
                    String gotType = rs.getString("type");
                    System.out.println('{' + gotName + ',' + gotType + '}');
                }
            }
        }
    }


    /*
    价格排序方法
     */
    static void SortedQuery(String sortOrder) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //创建集合存储商品
        ArrayList<Product> products = new ArrayList<>();
        //把数据库的商品写入集合
        String getAllSql = "select * from products;";
        try(Connection con = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)){
            try(PreparedStatement ps = con.prepareStatement(getAllSql)){
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    int maxQuantity = rs.getInt("max_quantity");
                    //日期格式化对象
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    //字符串转LocalDateTime
                    LocalDateTime ldt = LocalDateTime.parse(rs.getString("datetime"),dtf);
                    //把对象添加到products
                    products.add(new Product(name, id, Type.valueOf(type), price, quantity, maxQuantity, ldt));
                }
                switch(sortOrder){
                    case "id":{
                        Collections.sort(products, Comparator.comparing(Product::getId));
                        break;
                    }case "name":{
                        Collections.sort(products, Comparator.comparing(Product::getName));
                        break;
                    } case "type":{
                        Collections.sort(products, Comparator.comparing(Product::getType));
                        break;
                    } case "price":{
                        //对products中所有对象按价格排序
                        Collections.sort(products,(o1,o2) -> {
                                    BigDecimal ret = new BigDecimal(o1.getPrice()).divide(new BigDecimal(o2.getPrice()), 2, RoundingMode.CEILING);
                                    return ret.compareTo(new BigDecimal(0));
                                }
                        );
                        break;
                    } case "quantity":{
                        Collections.sort(products, Comparator.comparing(Product::getQuantity));
                        break;
                    } case "renewTime":{
                        Collections.sort(products, Comparator.comparing(Product::getRenewTime));
                        break;
                    } default:{
                        throw new InputException(sortOrder+"这个排序依据不存在。");
                    }
                }
            }
        }
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
