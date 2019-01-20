package com.junbao.factory.mooc;

public class MCFactory implements PersonFactory{
    @Override
    public Boy getBoy() {
        return new MCBoy();
    }

    @Override
    public Girl getGirl() {
        return new MCGirl();
    }
}
