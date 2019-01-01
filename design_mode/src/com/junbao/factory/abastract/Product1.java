package com.junbao.factory.abastract;

/**
 * @ClassName: Product1
 * @author: ganfengbao
 * @Date: 2018/12/20 17:39
 */
public class Product1 implements IProduct1 {
    @Override
    public void show() {
        System.out.println("这是1型产品");
    }
}
