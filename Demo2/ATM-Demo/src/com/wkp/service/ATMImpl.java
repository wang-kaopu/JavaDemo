package com.wkp.service;

import com.wkp.po.Account;
import com.wkp.dao.ATM;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class ATMImpl implements ATM {
    private ArrayList<Account> accounts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    @Override
    public void login() {
        //1.如果无账户可用
        if(accounts.isEmpty()){
            System.out.println("当前系统无账户。");
            return;
        }
        while (true) {
            //2.输入卡号
            System.out.println("请输入卡号：");
            String cardId = sc.next();
            //3.判断是否存在这个账户
            Account acc = getAccountById(cardId);
            if(acc == null){
                System.out.println("该账户不存在。");
            }else{
                System.out.println("请输入密码：");
                String key = sc.next();
                //4.判断密码是否正确
                if(acc.getKey().equals(key)){
                    System.out.println(acc.getName()+"登陆成功，卡号是："+acc.getCardId());
                    this.usingAccount = acc;
                    break;
                }else{
                    System.out.println("密码不正确，请确认");
                }
            }
        }
    }
    private final String URL;
    private final String USER_NAME;
    private final String USER_PASSWORD;
    private Account usingAccount;

    public ATMImpl() throws Exception {
        //获取数据库配置
        try(InputStream is = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(is);
            URL = properties.getProperty("URL");
            USER_NAME = properties.getProperty("USER_NAME");
            USER_PASSWORD = properties.getProperty("USER_PASSWORD");
        }
        //获取表，生成账户list
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sql = "select * from users";
        try(Connection conn = DriverManager.getConnection(URL,USER_NAME,USER_PASSWORD)){
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String name = rs.getString("name");
                    String phoneNumber = rs.getString("phone_number");
                    String identityId = rs.getString("identity_id");
                    String cardId = rs.getString("card_id");
                    BigDecimal money = rs.getBigDecimal("money");
                    String key = rs.getString("key");
                    this.accounts.add(new Account(name,phoneNumber,identityId,cardId,money,key));
                }
            }
        }
    }

    private Account getAccountById(String id){
        for (Account account : accounts) {
            if(account.getCardId().equals(id)){
                return account;
            }
        }
        return null;
    }

    public void start(){
        System.out.println("==进入系统==");
    }

    public void menu() throws Exception {
        while (true) {
            System.out.println("1.存款");
            System.out.println("2.取款");
            System.out.println("3.转账");
            int option = sc.nextInt();
            sc.nextLine();
            switch(option){
                case 1:{
                    //存款
                    if(deposit()){
                        System.out.println("存入成功，当前余额为"+usingAccount.getMoney());
                    }else{
                        System.out.println("存入失败，当前余额为"+usingAccount.getMoney());
                    }
                    break;
                } case 2:{
                    //取款
                    if(withdraw()){
                        System.out.println("取出成功，当前余额为"+usingAccount.getMoney());
                    }else{
                        System.out.println("取出失败，当前余额为"+usingAccount.getMoney());
                    }
                    break;
                } case 3:{
                    if(transfer()){
                        System.out.println("转账成功，当前余额为："+usingAccount.getMoney());
                    }else{
                        System.out.println("转账失败，当前余额为："+usingAccount.getMoney());
                    }
                    break;
                } default:{
                    System.out.println("没有该操作，请重新选择。");
                    break;
                }
            }
        }
    }

    public synchronized Boolean deposit() throws Exception {
        System.out.println("请输入存入的金额：");
        BigDecimal addedMoney = sc.nextBigDecimal();
        sc.nextLine();
        System.out.println("正在存入中……");
        Class.forName("com.mysql.cj.jdbc.Driver");
        BigDecimal money = usingAccount.getMoney().add(addedMoney);
        String id = usingAccount.getCardId();
        String sql = "update users set money = ? where card_id = ?;";
        try(Connection conn = DriverManager.getConnection(URL,USER_NAME,USER_PASSWORD)){
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setObject(1,money);
                ps.setObject(2,id);
                usingAccount.setMoney(money);
                return ps.executeUpdate()>0;
            }
        }
    }

    public synchronized Boolean withdraw() throws Exception {
        System.out.println("请输入取出的金额：");
        BigDecimal withdrewMoney = sc.nextBigDecimal();
        sc.nextLine();
        if(withdrewMoney.compareTo(usingAccount.getMoney())>0){
            System.out.println("账户金额不足。");
            return false;
        }
        System.out.println("正在取出中……");
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(InputStream is = new FileInputStream("db.properties")){
            BigDecimal money = usingAccount.getMoney().subtract(withdrewMoney);
            String id = usingAccount.getCardId();
            String sql = "update users set money = ? where card_id = ?;";
            try(Connection conn = DriverManager.getConnection(URL,USER_NAME,USER_PASSWORD)){
                try(PreparedStatement ps = conn.prepareStatement(sql)){
                    ps.setObject(1,money);
                    ps.setObject(2,id);
                    usingAccount.setMoney(money);
                    return ps.executeUpdate()>0;
                }
            }
        }
    }

    public synchronized Boolean transfer() throws Exception{
        System.out.println("请输入要转账的卡号：");
        String targetCardId = sc.next();
        sc.nextLine();
        if(getAccountById(targetCardId)!=null){
            Account targetAccount = getAccountById(targetCardId);
            System.out.println("请输入要转账的金额：");
            BigDecimal targetMoney = sc.nextBigDecimal();
            if(targetMoney.compareTo(usingAccount.getMoney())>0){
                System.out.println("转账金额超出当前账户余额。");
                return false;
            }else{
                Class.forName("com.mysql.cj.jdbc.Driver");
                try(Connection conn = DriverManager.getConnection(URL,USER_NAME,USER_PASSWORD)){
                    String sql = "update users set money = ? where card_id = ?;";
                    try(PreparedStatement p = conn.prepareStatement("begin;")){
                        try(PreparedStatement ps = conn.prepareStatement(sql)){
                            usingAccount.setMoney(usingAccount.getMoney().subtract(targetMoney));
                            ps.setObject(1,usingAccount.getMoney());
                            ps.setObject(2,usingAccount.getCardId());
                        }
                        try(PreparedStatement ps = conn.prepareStatement(sql)){
                            ps.setObject(1,targetAccount.getMoney().add(targetMoney));
                            ps.setObject(2,targetAccount.getCardId());
                        }
                    }
                    try(PreparedStatement p = conn.prepareStatement("commit;")){
                    }
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter("bills.txt"))){
                        String record = LocalDateTime.now()+"\r\n"+usingAccount.getName()+":"+usingAccount.getCardId()+"向"+
                                targetAccount.getName()+":"+targetAccount.getCardId()+"转账"+targetMoney+"元。";
                        writer.append(record);
                        writer.newLine();
                        writer.append("---------------------------------------");
                    }
                    return  true;
                }
            }
        }else{
            System.out.println("账户不存在。");
            return false;
        }

    }
}
