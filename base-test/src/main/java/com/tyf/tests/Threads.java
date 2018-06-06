package com.tyf.tests;

public class Threads implements Runnable {
    public static int count=0;
    @Override

    public void run() {
        synchronized (Threads.class){
            for(int i=0;i<100000;i++){
                count++;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int a=0;a<100;a++){
        Thread tt=  new Thread(new Threads());
        tt.start();
        }
        Thread.sleep(500);
        System.out.println("count: "+count);
    }
}
