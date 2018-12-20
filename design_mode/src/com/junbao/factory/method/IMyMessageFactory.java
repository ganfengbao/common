package com.junbao.factory.method;

/**
 * 工厂方法模式_工厂接口
 */
public interface IMyMessageFactory {
    public IMyMessage createMessage(String messageType);
}
