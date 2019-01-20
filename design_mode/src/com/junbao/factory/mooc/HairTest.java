package com.junbao.factory.mooc;

public class HairTest {
    public static void main(String[] args) {
//        HairInterface left = new LeftHair();
//        left.draw();
        HairFactory hairFactory = new HairFactory();
//        HairInterface left = hairFactory.getHair("left");
//        left.draw();
//        HairInterface left = hairFactory.getHairByClass("com.junbao.factory.mooc.LeftHair");
//        left.draw();
        HairInterface left = hairFactory.getHairByClassKey("left");
        left.draw();
        HairInterface right = hairFactory.getHairByClassKey("right");
        right.draw();
        HairInterface in = hairFactory.getHairByClassKey("in");
        in.draw();

        PersonFactory personFactory = new MCFactory();
        Boy boy = personFactory.getBoy();
        boy.drawMan();

        PersonFactory personFactory1 = new HNFactory();
        Girl girl = personFactory1.getGirl();
        girl.drawWoman();

    }
}
