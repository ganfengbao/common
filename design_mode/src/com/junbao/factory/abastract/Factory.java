package com.junbao.factory.abastract;

/**
 * @ClassName: Factory
 * @author: ganfengbao
 * @Date: 2018/12/20 17:42
 */
public class Factory implements IFactory {

    @Override
    public IProduct1 createProduct1() {
        return new Product1();
    }

    @Override
    public IProduct2 createProduct2() {
        return new Product2();
    }
}
