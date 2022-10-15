package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe;

public interface Observerable {

    /**
     * 注册事件
     */
    void registerObserver(Observer observer);

    /**
     * 取消事件
     */
    void cancelObserver(Observer observer);


    /**
     * 更新事件 发送一条通知
     */
    void notifyObserver();
    
}
