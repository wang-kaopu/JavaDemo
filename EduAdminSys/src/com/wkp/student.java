package com.wkp;

import java.awt.*;

public class student extends People{
    private int score;
    private String teacherId;
//
//    public student(int score, int teacherId) {
//        this.score = score;
//        this.teacherId = teacherId;
//    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public student(){
    };

    public student(String name, String id){
        super(name,id);
    }

    public student(String name, String id, int score, String teacherId) {
        super(name, id);
        this.score = score;
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + this.getId() +
                ", name=" + this.getName() +
                ", score=" + score +
                ", teacherId=" + teacherId +
                '}';
    }

    public static void Menu(){
        System.out.println("Welcome. Please choose:");
        System.out.println("1.Update information");
        System.out.println("2.Show your score");
        System.out.println("3.Show your teacher");
    }
}
