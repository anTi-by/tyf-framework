package com.tyf.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserTest {
    public static void main(String[] args) {
//        ProxyFlower proxyFlower =new ProxyFlower(new RoseFlower());
//        proxyFlower.getFlower("reos");
        Flower sub=new RoseFlower();
        MyProxy proxy=new MyProxy(sub);
        Flower oo=(Flower)Proxy.newProxyInstance(sub.getClass().getClassLoader(),new Class[]{Flower.class},proxy);
        oo.getFlower("nio");
    }
}
