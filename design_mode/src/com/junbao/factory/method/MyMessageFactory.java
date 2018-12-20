package com.junbao.factory.method;

import java.util.HashMap;
import java.util.Map;

/**
 * ��������ģʽ_����ʵ��
 * @ClassName: MyMessageFactory
 * @author: ganfengbao
 * @Date: 2018/12/20 16:49
 */
public class MyMessageFactory implements IMyMessageFactory{

    @Override
    public IMyMessage createMessage(String messageType) {
        // ����ķ�ʽ�ǣ�������֪���Լ���Ҫʲô��Ʒ�����������ֲ�Ʒ��ȫ�ɹ��������������ﲻӦ�ô�����������Ĳ�����
        IMyMessage myMessage;
        Map<String, Object> messageParam = new HashMap<String, Object>();
        // ����ĳЩ����ȥѡ�񾿾�������һ�������ʵ�ֶ����������Դ���ģ�Ҳ���Դ�����;����ȡ��
        // sms
        if ("SMS".equals(messageType)) {
            myMessage = new MyMessageSms();
            messageParam.put("PHONENUM", "123456789");
        } else
            // OA����
            if ("OA".equals(messageType)) {
                myMessage = new MyMessageOaTodo();
                messageParam.put("OAUSERNAME", "testUser");
            } else
                // email
                if ("EMAIL".equals(messageType)) {
                    myMessage = new MyMessageEmail();
                    messageParam.put("EMAIL", "test@test.com");
                } else
                // Ĭ������email�����Ʒ
                {
                    myMessage = new MyMessageEmail();
                    messageParam.put("EMAIL", "test@test.com");
                }
        myMessage.setMessageParam(messageParam);
        return myMessage;
    }
}
