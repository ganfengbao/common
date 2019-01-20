package com.junbao.strategy;

import com.junbao.strategy.impl.FlyWithWin;

public class RedHeadDuck extends Duck {

    public RedHeadDuck() {
        super();
        super.setFlyingStrategy(new FlyWithWin());
    }

    @Override
    public void display() {
        System.out.println("我的头是红色的");
    }
}
