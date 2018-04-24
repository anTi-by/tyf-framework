package com.tyf.dl;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
    int rs=10; //人数
         int count =100;
         while(rs>0){
             Random r=new Random();
            int s=r.nextInt(count);

            count=count-s;
            System.out.println(s+"fff"+count);
            rs--;

         }
    }
}
