package com.wkp;

public class ball {
    private int number;
    private String color;

    public ball(int number, String color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ball{" +
                "number=" + number +
                ", color='" + color + '\'' +
                '}';
    }
}
