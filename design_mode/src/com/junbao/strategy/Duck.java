package com.junbao.strategy;

/**
 * 超类，所有的鸭子都需要继承此类
 * 抽象了鸭子的行为：显示和鸣叫
 */
public abstract class Duck {

    /**
     * 鸭子发出叫声
     * 通用行为，由超类实现
     */
    public void quack () {
        System.out.println("嘎嘎嘎");
    }

    /**
     * 显示鸭子的外观
     * 鸭子的外观各不相同，声明为abstract，由子类实现
     */
    public abstract void  display();

    private FlyingStrategy flyingStrategy;

    public void setFlyingStrategy(FlyingStrategy flyingStrategy) {
        this.flyingStrategy = flyingStrategy;
    }

    public void fly() {
        flyingStrategy.performFly();
    }
}
