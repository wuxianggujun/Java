package com.wuxianggujun.muxin.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName my_friends
 */
@Data
public class MyFriends implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 用户id
     */
    private String myUserId;

    /**
     * 我朋友的用户id
     */
    private String myFriendUserId;

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
        MyFriends other = (MyFriends) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMyUserId() == null ? other.getMyUserId() == null : this.getMyUserId().equals(other.getMyUserId()))
            && (this.getMyFriendUserId() == null ? other.getMyFriendUserId() == null : this.getMyFriendUserId().equals(other.getMyFriendUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMyUserId() == null) ? 0 : getMyUserId().hashCode());
        result = prime * result + ((getMyFriendUserId() == null) ? 0 : getMyFriendUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", myUserId=").append(myUserId);
        sb.append(", myFriendUserId=").append(myFriendUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}