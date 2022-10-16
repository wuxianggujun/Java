package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe.event.data;

public class GroupMessage extends Message {
    public GroupMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    
}
