package com.wuxianggujun.muxin.pojo;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Id;

/**
 * 
 * @TableName users
 */
@Data
public class Users implements Serializable {
    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 小头像
     */
    private String faceImage;

    /**
     * 大头像
     */
    private String faceImageBig;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 二维码
     */
    private String qrcode;

    /**
     * 每一台手机会有一个cid
     */
    private String cid;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Users other = (Users) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getFaceImage() == null ? other.getFaceImage() == null : this.getFaceImage().equals(other.getFaceImage()))
            && (this.getFaceImageBig() == null ? other.getFaceImageBig() == null : this.getFaceImageBig().equals(other.getFaceImageBig()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getQrcode() == null ? other.getQrcode() == null : this.getQrcode().equals(other.getQrcode()))
            && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getFaceImage() == null) ? 0 : getFaceImage().hashCode());
        result = prime * result + ((getFaceImageBig() == null) ? 0 : getFaceImageBig().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getQrcode() == null) ? 0 : getQrcode().hashCode());
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", faceImage=").append(faceImage);
        sb.append(", faceImageBig=").append(faceImageBig);
        sb.append(", nickname=").append(nickname);
        sb.append(", qrcode=").append(qrcode);
        sb.append(", cid=").append(cid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}