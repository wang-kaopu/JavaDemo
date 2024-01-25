package com.wkp;

public class People {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public People(){

    }
    public People(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
