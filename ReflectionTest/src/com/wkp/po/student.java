package com.wkp.po;

public class student extends person{
    public int score;

    public student(int score) {
        this.score = score;
    }

    public student(String name, int score) {
        super(name);
        this.score = score;
    }

    public int getScore() {
        return 99;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "student{" +
                "name=" + this.getName() +
                ",score=" + score +
                '}';
    }
}
