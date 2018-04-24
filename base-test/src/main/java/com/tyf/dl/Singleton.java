package com.tyf.dl;

public class Singleton {
    private Singleton(){

    }
   private static Singleton istance=new Singleton();


    public static Singleton getInstance() {
        return istance;
    }
}
