package com.wkp;

import java.util.*;

public class test {
    public static void main(String[] args) {
        //1.创建集合
        Scanner scanner = new Scanner(System.in);
        List<teacher> teachers = new ArrayList<>();
        List<student> students = new ArrayList<>();
        Collections.addAll(teachers, new teacher("Alice","101"),
                new teacher("Bob","102"));
        Collections.addAll(students, new student("Cindy","001", 89, "101"),
                new student("David","002", 99, "102"));

        //2.进入系统
        int identity = 0;
        System.out.println("Please input your id：");
        String InputId = scanner.nextLine();
        for(teacher t : teachers){
            if(Objects.equals(t.getId(), InputId)){
                identity = 1;
                break;
            }
        }
        for(student s : students){
            if(Objects.equals(s.getId(), InputId)){
                identity = 2;
                break;
            }
        }
        if(identity == 0){
            throw new InputException("This one doesn't exist.");
        }
        while(true){
        //教师使用
        if (identity == 1) {
            teacher operatorTeacher = (teacher)new SearchPeople().Search(teachers, InputId);
            //教师功能选择
            teacher.menu();
            int opt = scanner.nextInt();
            scanner.hasNextLine();
            if (opt == 1) {
                //更新分数
                System.out.println("Now ready to update score.");
                System.out.println("Please input the student's id:");
                scanner.nextLine();
                String stuId = scanner.nextLine();
                student searched = new SearchPeople<student>().Search(students, stuId);
                //while (scanner.hasNextLine()) ;
                System.out.println("Please input new score:");
                int newScore = scanner.nextInt();
                operatorTeacher.updateStuScore(searched, newScore);
            } else if (opt == 2) {
                //新增学生
                System.out.println("Now ready to add student.");
                System.out.println("Please input the student's information:");
                scanner.nextLine();
                String freshId = scanner.nextLine();
                String freshName = scanner.nextLine();
                int freshScore = scanner.nextInt();
                operatorTeacher.addStudent(students,
                        new student(freshId,freshName,freshScore,operatorTeacher.getId()));
            } else if (opt == 3) {
                //查找学生
                scanner.nextLine();
                System.out.println("Please input the student's id:");
                String stuId = scanner.nextLine();
                System.out.println(new SearchPeople().Search(students, stuId));
            } else if (opt == 4) {
                //查看所有学生
                for(student s:students){
                    if(Objects.equals(s.getTeacherId(), operatorTeacher.getId())){
                        System.out.println(s);
                    }
                }
            } else {
                throw new InputException("The one doesn't exist.");
            }
        }
        //学生功能
        else if (identity == 2) {
            student studentOperator = (student) new SearchPeople().Search(students, InputId);
            student.Menu();
            int opt = scanner.nextInt();
            if(opt == 1){
                System.out.println("Please input your correct name:");
                scanner.nextLine();
                String newName = scanner.nextLine();
                studentOperator.setName(newName);
                //scanner.nextLine();
                System.out.println("Please input your correct id:");
                String newId = scanner.nextLine();
                studentOperator.setId(newId);
            }else if(opt == 2){
                System.out.println("Your score is:" + studentOperator.getScore() + '.');
            } else if (opt == 3) {
                for(teacher t : teachers){
                    if(Objects.equals(t.getId(), studentOperator.getTeacherId())){
                        System.out.println(t);
                    }
                }
            }else{
                throw new InputException("The one doesn't exist.");
            }
        }
        else {
            throw new InputException("The one doesn't exist.");
        }
    }
    }

}
