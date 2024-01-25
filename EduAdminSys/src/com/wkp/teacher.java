package com.wkp;

import java.util.Collection;
import java.util.List;

public class teacher extends People {
    public teacher() {
    }

    public teacher(String name, String id) {
        super(name, id);
    }

    public static void menu(){
        System.out.println("Welcome. Please choose:");
        System.out.println("1.Update score");
        System.out.println("2.Add student");
        System.out.println("3.Search in your students");
        System.out.println("4.Show all your students");
    }

    public void updateStuScore(student stu, int score){
        stu.setScore(score);
    }

    public void addStudent(List<student> list, student newStudent){
        list.add(newStudent);
    }
}
