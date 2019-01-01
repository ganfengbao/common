package com.junbao.factory.abastract;

/**
 * @ClassName: Client
 * @author: ganfengbao
 * @Date: 2018/12/20 17:42
 */
public class Client {
    public static void main(String[] args) {
        IFactory factory = new Factory();
        factory.createProduct1().show();
        factory.createProduct2().show();
    }
}
