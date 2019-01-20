package com.junbao.strategy;

public class DuckTest {
    public static void main(String[] args) {
        System.out.println("测试鸭子程序开始...");
        Duck duck = null;
//        duck = new MallardDuck();
        duck = new RubberDuck();
        duck.quack();
        duck.display();
        duck.fly();

        System.out.println("程序测试完毕");
    }
}
