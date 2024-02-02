package com.wkp;

import com.wkp.util.Calculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> operators = new ArrayList<>();
        Collections.addAll(operators,"+","-","*","/","%");

        while (true) {
            System.out.println("请输入第一个运算数：");
            double d1 = scanner.nextDouble();
            if(d1<-1.79E+308||d1>1.79E+308){
                System.out.println("输入的运算数超出范围。");
                break;
            }else{
                scanner.nextLine();
            }
            System.out.println("请输入运算符：");
            String o = scanner.nextLine();
            System.out.println("请输入第二个运算数：");
            double d2 = scanner.nextDouble();
            if(d2<-1.79E+308||d2>1.79E+308){
                System.out.println("输入的运算数超出范围。");
                break;
            }else{
                scanner.nextLine();
            }
            if(operators.contains(o))
            {
                switch (o){
                    case "+":{
                        System.out.println(Calculator.Plus(d1, d2));
                        break;
                    } case "-":{
                        System.out.println(Calculator.Sub(d1, d2));
                        break;
                    } case "*":{
                        System.out.println(Calculator.Mul(d1, d2));
                        break;
                    } case "/":{
                        System.out.println(Calculator.Div(d1, d2));
                        break;
                    } case "%":{
                        System.out.println(Calculator.Mod(d1, d2));
                        break;
                    }
                }
            } else{
                System.out.println("输入有误，请重新输入。");
            }
        }
    }
}
