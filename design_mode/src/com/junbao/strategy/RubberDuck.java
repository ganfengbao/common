package com.junbao.strategy;

import com.junbao.strategy.impl.FlyNoWay;

public class RubberDuck extends Duck {
    public RubberDuck() {
        super();
        super.setFlyingStrategy(new FlyNoWay());
    }

    @Override
    public void display() {
        System.out.println("我是大黄鸭");
    }

    @Override
    public void quack() {
        System.out.println("嘎~嘎~嘎~");
    }
}
