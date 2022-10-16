package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data;

public class PrivateMessage extends Message{
    public PrivateMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
