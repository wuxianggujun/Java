package com.wuxianggujun.muxin.pojo.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class UsersVo implements Serializable {
    private String id;
    private String username;
    private String faceImage;
    private String faceImageBig;
    private String nickname;
    private String qrcode;
}