package com.junbao.factory.method;

/**
 * ��������ģʽ_oa�����Ʒ
 * @ClassName: MyMessageOaTodo
 * @author: ganfengbao
 * @Date: 2018/12/20 17:02
 */
public class MyMessageOaTodo extends MyAbstractMessage {
    @Override
    public void sendMesage() throws Exception {
        // TODO Auto-generated method stub
        if (null == getMessageParam()
                || null == getMessageParam().get("OAUSERNAME")
                || "".equals(getMessageParam().get("OAUSERNAME"))) {
            throw new Exception("����OA����,��Ҫ����OAUSERNAME����");// Ϊ�˼�����쳣Ҳ���Զ�����
        }// ����Ĳ�������ͱȽ϶��˲�һһ������

        System.out
                .println("����OA���죬����֪ͨ��" + getMessageParam().get("OAUSERNAME"));
    }
}
