package com.wkp.po;

public class person {
    public String name;

    public person(){

    }
    public person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

}
