package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event;

import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data.GroupMessage;
import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data.Message;

public interface GroupMessageListener extends EventListener<GroupMessage> {
    @Override
    void processEvent(GroupMessage message);
}
