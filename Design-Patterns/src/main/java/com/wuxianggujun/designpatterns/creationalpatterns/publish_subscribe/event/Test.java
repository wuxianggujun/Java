package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event;

import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data.GroupMessage;
import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data.PrivateMessage;
import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.impl.GroupMessageListenerImpl;
import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.impl.PrivateMessageListenerImpl;

public class Test {
    public static void main(String[] args) {
        EventListener groupMessageListener = new GroupMessageListenerImpl();
        EventListener privateMessageListener = new PrivateMessageListenerImpl();

        ListenerSupport listenerSupport = new ListenerSupport();
        listenerSupport.registry(groupMessageListener);
        listenerSupport.registry(privateMessageListener);

        listenerSupport.triggerEvent(new GroupMessage("����Ⱥ��Ϣ"));
        listenerSupport.triggerEvent(new PrivateMessage("����˽����Ϣ"));
    }
}
