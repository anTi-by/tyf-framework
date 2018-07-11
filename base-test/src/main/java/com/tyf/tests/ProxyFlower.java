package com.tyf.tests;

public class ProxyFlower implements Flower {
    private Flower sub;

    public ProxyFlower(Flower sub) {
        this.sub = sub;
    }

    @Override
    public String getFlower(String name) {
        System.out.println("before");
        String res=sub.getFlower(name);
        System.out.println(res);
        System.out.println("after");
        return res;
    }
}
