package com.wuxianggujun.muxin.netty;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMsg implements Serializable {
    private static final long serialVersionUID = 5169086302005565618L;
    
    private String msg; //聊天消息
    private String senderId;//发送者id
    private String receiverId;//接受者ID
    private String msgId;//用于消息的签收
}
