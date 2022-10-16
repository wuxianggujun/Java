package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.impl;

import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.PrivateMessageListener;
import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data.PrivateMessage;

public class PrivateMessageListenerImpl implements PrivateMessageListener {
    
    @Override
    public void processEvent(PrivateMessage message) {
        System.out.println("private message = " + message.getMessage());
    }
}
