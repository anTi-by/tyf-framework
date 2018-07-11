package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LamdaTests {
    interface ILambdaTest1{
        void print(String s);
    }

    public static void main(String[] args) {

        Ainimils ainimils=(x)->{return x ;};
        ainimils.run("f");
     LambdaUse(n-> {
         if (n.equals("s")){
             System.out.println(n);
         }else{
             System.out.println("dddd");
         }
     },"s");
    }

    public static void LambdaUse(ILambdaTest1 lambda,String string){
        lambda.print(string);
    }
}
