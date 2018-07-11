package com.tyf.tests;

public class DaffodilFlower implements Flower{
    @Override
    public String getFlower(String name) {
        return "我是水仙花："+name;
    }
}
