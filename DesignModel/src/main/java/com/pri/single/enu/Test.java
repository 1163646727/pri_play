package com.pri.single.enu;

import com.pri.single.SingleHunger;

import java.lang.reflect.Constructor;

public class Test {
    public static void main(String [] args){
        Object user = User.getInstance();
        Object user2 = User.getInstance();
        System.out.println(user==user2);

        System.out.println("测试反射破坏单列模式");
        try {
            Constructor<User> constructor = (Constructor<User>) user.getClass().getDeclaredConstructor();
            constructor.setAccessible(true);
            User user3 = constructor.newInstance();
            System.out.println(user==user3);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}