package com.junbao.factory.method;

/**
 * ��������ģʽ_�����ӿ�
 */
public interface IMyMessageFactory {
    public IMyMessage createMessage(String messageType);
}
