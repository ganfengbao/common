package com.junbao.singleton;

/*
* 单例模式 -- singleton
* 应用场景：有些对象只需要一个就足够了。
* 作用：保证整个应用程序中某个实例有且只有一个
* 类型：饿汉模式 懒汉模式
* */
public class Singleton {
    /*
    * 饿汉模式
    * */
//    1、将构造方法私有化, 不允许外界直接创建对象
//    private Singleton() { }
//
//    // 2、创建类的实例
//    private static Singleton instance = new Singleton();
//
//    //3、提供一个用于获取实例的方法
//    public static Singleton getInstance() {
//        return instance;
//    }

    /*
    * 懒汉模式
    * */
    private Singleton() { }

    // 2、声明一个实例
    private static Singleton instance;

    //3、提供一个用于获取实例的方法
    public static Singleton getInstance() {
        if (instance != null) {
            instance = new Singleton();
        }
        return instance;
    }
}
