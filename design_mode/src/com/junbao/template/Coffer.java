package com.junbao.template;

/**
 * 具体子类，提供了咖啡制备的具体实现
 */
public class Coffer extends RefreshBeverage {
    @Override
    protected void addCondiments() {
        System.out.println("咖啡加入调味料");
    }

    @Override
    protected void brew() {
        System.out.println("泡制咖啡");
    }

    @Override
    protected boolean isCoustomerWantsCondiments() {
        return false;
    }
}
