package com.wkp.util;

import com.wkp.dao.ProductDao;
import com.wkp.po.Product;
import com.wkp.service.Type;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ProductManagementUtil {
    static Scanner scanner = new Scanner(System.in);

    public static void menu(){
    System.out.println("请选择要进行的操作：");
    System.out.println("1.增加商品 2.删除商品");
    System.out.println("3.查询单个商品 4.排序并查询所有商品");
    System.out.println("5.修改商品 0.退出程序");
}

    public static void AddProduct(){
        System.out.println("请输入商品的名称：");
        String name = scanner.nextLine();
        System.out.println("请输入商品的类型：");
        Type type = Type.valueOf(scanner.nextLine());
        System.out.println("请输入商品的价格：");
        Double price = scanner.nextDouble();
        System.out.println("请输入商品的库存数量：");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入商品的最大库存数量：");
        int maxQuantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("正在添加商品……");
        try {
            ProductDao.Add(new Product(name, type, price, quantity, maxQuantity, LocalDateTime.now()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void DeleteProduct() throws Exception {
        ProductDao.SortedQuery("id");
        System.out.println("请输入要删除的商品的id：");
        String id = scanner.nextLine();
        ProductDao.Del(id);
    }

    public static void ModifyProduct() throws Exception {
        ProductDao.SortedQuery("id");
        System.out.println("请输入要修改的商品的id：");
        String id = scanner.nextLine();
        System.out.println("请输入修改后商品的名称：");
        String name = scanner.nextLine();
        System.out.println("请输入修改后商品的类型：");
        Type type = Type.valueOf(scanner.nextLine());
        System.out.println("请输入修改后商品的价格：");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("请输入修改后商品的库存数量：");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入修改后商品的最大库存数量：");
        int maxQuantity = scanner.nextInt();
        scanner.nextLine();
        ProductDao.Modify(id, name, type, price, quantity, maxQuantity, LocalDateTime.now());
    }

    public static void SelectSingleProduct() throws Exception {
        System.out.println("请输入要查询的商品的编号：");
        String id = scanner.nextLine();
        ProductDao.Select(id);
    }

    public static void SortAndQuery() throws Exception {
        System.out.println("请选择排序方式：");
        String Order = scanner.nextLine();
        ProductDao.SortedQuery(Order);
    }

}
