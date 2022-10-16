package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.impl;

import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.GroupMessageListener;
import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data.GroupMessage;

public class GroupMessageListenerImpl implements GroupMessageListener {
    
    @Override
    public void processEvent(GroupMessage message) {
        System.out.println("group message = " + message.getMessage());
    }
}
