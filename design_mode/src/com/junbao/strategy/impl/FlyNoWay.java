package com.junbao.strategy.impl;

import com.junbao.strategy.FlyingStrategy;

public class FlyNoWay implements FlyingStrategy {
    @Override
    public void performFly() {
        System.out.println("我不会飞行");
    }
}
