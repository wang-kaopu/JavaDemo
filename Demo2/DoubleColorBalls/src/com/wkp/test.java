package com.wkp;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Random random = new Random();
        //1.生成随机球
        ArrayList<ball> balls = new ArrayList<>();
        for (int i = 0 ; i < 6; i++){
            balls.add(new ball(random.nextInt(1,31), "Red"));
        }
        balls.add(new ball(random.nextInt(1,31),"Blue"));

        //2.输入抽到的双色球号码
        Scanner scanner = new Scanner(System.in);
        int gotNumber = 0;
        do{
            gotNumber= scanner.nextInt();
            if(gotNumber > 0 && gotNumber <=30){
                break;
            }else{
                System.out.println("输入有误，请重新输入");
            }
        }while(!(gotNumber > 0 && gotNumber <=30));

        System.out.println("开奖的球是：");
        //3.遍历集合，比对号码
        for (ball b : balls){
            System.out.println(b);
            if(b.getNumber()==gotNumber){
                System.out.println("中奖了。");
                if(b.getColor().equals("Blue")){
                    System.out.println("蓝色球奖金：五毛");
                }else {
                    System.out.println("红色球奖金：一毛");
                }
            }
        }


    }
}
