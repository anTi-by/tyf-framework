package com.tyf.tests;

public class ThreadLocals {
    public ThreadLocals() {
    }

   private static final ThreadLocal<String> co=new ThreadLocal<>();

    public static String getConn() {
        String con = co.get();
        if (con == null) {
            co.set("fff"+Thread.currentThread());
        }
        return con;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocals s=new ThreadLocals();
        s.s();
        System.out.println(co);
    }
    public void  s() throws InterruptedException {
        int a=10;
        for (int i=0;i<a;i++){
            Thread tt=  new Thread(new Threads());
            tt.start();
            Thread.sleep(500);
        }
    }
    class Threads implements Runnable {

        @Override

        public void run() {
            for (int i = 0; i <6; i++) {
                ThreadLocals.getConn();
                System.out.println(Thread.currentThread()+" :");
            }
        }
    }
}

