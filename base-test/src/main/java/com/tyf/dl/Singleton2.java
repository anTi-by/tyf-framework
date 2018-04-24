package com.tyf.dl;

public class Singleton2 {
    private Singleton2(){

    }
    private static Singleton2 istance;


    public static Singleton2 getInstance() {
        if (istance == null) {
            istance=new Singleton2();
        }
        return istance;
    }
}
