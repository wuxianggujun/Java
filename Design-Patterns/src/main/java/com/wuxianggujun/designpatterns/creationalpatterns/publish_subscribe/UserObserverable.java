package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe;

public class UserObserverable implements Observer {
    private String name;

    public UserObserverable(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name+" 订阅者接收消息：" + message);
    }
}
