package com.junbao.factory.method;

import java.util.Map;

/**
 * 工厂方法模式_产品接口
 * @ClassName: IMyMessage
 * @author: ganfengbao
 * @Date: 2018/12/20 16:48
 */
public interface IMyMessage {

    public Map<String, Object> getMessageParam();

    public void setMessageParam(Map<String, Object> messageParam);

    public void sendMesage() throws Exception;// 发送 通知/消息
}
