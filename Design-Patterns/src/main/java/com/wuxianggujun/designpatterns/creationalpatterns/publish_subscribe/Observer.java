package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe;

public interface Observer {
    /***
     * 定义一个update 方法,当被观察者<微信公众号> 发布事件的时候,会执行该方法.
     * @param message
     */
    void update(String message);
}
