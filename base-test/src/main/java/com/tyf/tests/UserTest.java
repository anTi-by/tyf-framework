package com.tyf.tests;

public class UserTest {
    public static void main(String[] args) {
        UserEntity user=new UserEntity();
        System.out.println(user);
        System.out.println("=====================");
        inserData(user);
        System.out.println(user);
    }
    public  static void inserData(UserEntity userEntity){
        if (userEntity.getName()==null){
            userEntity.setName("张三");
            userEntity.setAge(14);
            userEntity.setDiscripe("市三好学生");
        }else{
            System.out.println("data is not null!");
        }
    }
}
