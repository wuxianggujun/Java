package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event;

import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data.GroupMessage;
import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data.Message;
import com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data.PrivateMessage;

import java.util.ArrayList;
import java.util.List;

public class ListenerSupport {
    private List<EventListener> listeners = new ArrayList<>();

    public void registry(EventListener listener) {
        listeners.add(listener);
    }

    public void triggerEvent(Message event) {
        for (EventListener listener : listeners) {
            if (listener instanceof GroupMessageListener && event instanceof GroupMessage) {
                listener.processEvent(event);
            }else if (listener instanceof PrivateMessageListener && event instanceof PrivateMessage) {
                listener.processEvent(event);
            }
        }
    }
}
