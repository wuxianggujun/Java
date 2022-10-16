package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event;

public interface EventListener<T> {
    void processEvent(T message);
    
}
