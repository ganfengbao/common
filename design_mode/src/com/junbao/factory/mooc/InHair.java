package com.junbao.factory.mooc;

/**
 * 中分发型
 */
public class InHair implements HairInterface {
    @Override
    public void draw() {
        System.out.println("实现了中分发型");
    }
}
