package com.wkp;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

//数据库配置类
public class DataSourceConfig {
    private String driver;//驱动
    private String url;//地址
    private String user_name;//用户名
    private String password;//密码
    private String max_size;//最大连接数量
    private String init_size;//初始连接数量
    private String auto_grow_size;//自动增长数量

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMax_size() {
        return max_size;
    }

    public void setMax_size(String max_size) {
        this.max_size = max_size;
    }

    public String getInit_size() {
        return init_size;
    }

    public void setInit_size(String init_size) {
        this.init_size = init_size;
    }

    public String getAuto_grow_size() {
        return auto_grow_size;
    }

    public void setAuto_grow_size(String auto_grow_size) {
        this.auto_grow_size = auto_grow_size;
    }

    @Override
    public String toString() {
        return "DataSourceConfig{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", max_size='" + max_size + '\'' +
                ", init_size='" + init_size + '\'' +
                ", auto_grow_size='" + auto_grow_size + '\'' +
                '}';
    }

    //在构造器中对属性进行初始化
    public DataSourceConfig(){
        Properties prop = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        try {
            //把字节流加载到prop
            prop.load(is);
            //获取所有形参，用反射
            for (Object o : prop.keySet()) {
                String fieldName = o.toString();
                Field field = this.getClass().getDeclaredField(fieldName);
                Method method = this.getClass().getMethod(fieldName, field.getType());
                method.invoke(this, prop.get(o));
            }
        } catch (IOException | NoSuchFieldException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


}
