package com.junbao.singleton;

public class Test {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        if (singleton1 == singleton2) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
