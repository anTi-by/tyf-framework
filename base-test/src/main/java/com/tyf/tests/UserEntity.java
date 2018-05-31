package com.tyf.tests;

import java.io.Serializable;

public class UserEntity implements Serializable {
    private  String name;
    private  String discripe;
    private  int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscripe() {
        return discripe;
    }

    public void setDiscripe(String discripe) {
        this.discripe = discripe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        System.out.println("调用方法");
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", discripe='" + discripe + '\'' +
                ", age=" + age +
                '}';
    }
}
