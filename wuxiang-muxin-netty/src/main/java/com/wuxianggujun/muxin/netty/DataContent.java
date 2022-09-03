package com.wuxianggujun.muxin.netty;


import lombok.Data;

import java.io.Serializable;

@Data
public class DataContent implements Serializable {
    private static final long serialVersionUID = -5627483012570636242L;

    private Integer action; //动作类型
    private ChatMsg chatMsg; //用户的聊天内容entity
    private String extand;//扩展字段


}
