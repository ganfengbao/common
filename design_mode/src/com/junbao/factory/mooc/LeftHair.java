package com.junbao.factory.mooc;

/**
 * 左偏方发型
 */
public class LeftHair implements HairInterface {
    @Override
    public void draw() {
        System.out.println("实现了左偏分发型");
    }
}
