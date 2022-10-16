package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event;

import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data.PrivateMessage;

public interface PrivateMessageListener extends EventListener<PrivateMessage> {
    @Override
    void processEvent(PrivateMessage message);
}
