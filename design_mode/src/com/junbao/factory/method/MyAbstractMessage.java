package com.junbao.factory.method;

import java.util.Map;

/**
 * ��������ģʽ_�����Ʒ��
 * @ClassName: MyAbstractMessage
 * @author: ganfengbao
 * @Date: 2018/12/20 17:00
 */
public class MyAbstractMessage implements IMyMessage {
    private Map<String, Object> messageParam;// ����������Ϊ������Ʒ����Ҫ��ԭ���Ͽ⡣����Ǹ��Զ���Ķ�������Ϊ�˲��������ʹ��Map��

    @Override
    public Map<String, Object> getMessageParam() {
        return messageParam;
    }

    @Override
    public void setMessageParam(Map<String, Object> messageParam) {
        this.messageParam = messageParam;
    }

    @Override
    public void sendMesage() throws Exception {

    }
}
