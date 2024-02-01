package com.wkp;

import com.wkp.service.InputException;
import com.wkp.util.ProductManagementUtil;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        //打印菜单选择操作
        while(true){
            ProductManagementUtil.menu();
            int i = scanner.nextInt();
            scanner.nextLine();
            if(i == 0){
                System.out.println("退出程序。");
                break;
            }
            switch (i){
                case 1:{
                    ProductManagementUtil.AddProduct();
                    System.out.println("添加成功。");
                    break;
                }
                case 2:{
                    ProductManagementUtil.DeleteProduct();
                    System.out.println("删除成功。");
                    break;
                }
                case 3:{    ProductManagementUtil.SelectSingleProduct();  break;  }
                case 4:{    ProductManagementUtil.SortAndQuery(); break;  }
                case 5:{
                    ProductManagementUtil.ModifyProduct();
                    System.out.println("修改成功。");
                    break;
                }
                default:{
                    throw new InputException(i+"并不是可选择的操作之一。");
                }
            }
        }
    }


}
