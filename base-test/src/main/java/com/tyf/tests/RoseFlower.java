package com.tyf.tests;

public class RoseFlower implements Flower {
    @Override
    public String getFlower(String name) {
        return "我是玫瑰花："+name;
    }
}
