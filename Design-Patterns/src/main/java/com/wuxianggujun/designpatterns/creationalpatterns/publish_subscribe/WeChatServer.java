package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe;

import java.util.ArrayList;
import java.util.List;

public class WeChatServer implements Observerable {
    private List<Observer> observerList;
    private String message;

    public WeChatServer() {
        observerList = new ArrayList<>();

    }

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void cancelObserver(Observer observer) {
        observerList.remove(observer);
    }

    /**
     * 通知每一个订阅者 接收信息
     */
    @Override
    public void notifyObserver() {
        for (Observer observer : observerList) {
            observer.update(message);
        }
    }

    /**
     * 公众号推送消息
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
        System.out.println("message = " + message);
        // 消息来临 通知每一个订阅者
        notifyObserver();
    }
}
