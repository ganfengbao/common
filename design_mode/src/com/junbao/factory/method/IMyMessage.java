package com.junbao.factory.method;

import java.util.Map;

/**
 * ��������ģʽ_��Ʒ�ӿ�
 * @ClassName: IMyMessage
 * @author: ganfengbao
 * @Date: 2018/12/20 16:48
 */
public interface IMyMessage {

    public Map<String, Object> getMessageParam();

    public void setMessageParam(Map<String, Object> messageParam);

    public void sendMesage() throws Exception;// ���� ֪ͨ/��Ϣ
}
