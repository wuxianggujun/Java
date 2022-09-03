package com.wuxianggujun.muxin.utils;

import java.io.Serializable;

/**
 * 自定义响应数据结构
 *
 * @param status 响应业务状态
 * @param msg    响应消息
 * @param data   封装响应数据
 */
public record JsonResult(Integer status, String msg, Object data) implements Serializable {

    public static JsonResult build(Integer status, String msg, Object data) {
        return new JsonResult(status, msg, data);
    }


    public static JsonResult ok() {
        return new JsonResult(200, "ok", null);
    }

    public static JsonResult errorMsg(String msg) {
        return build(500, msg, null);
    }

    public static JsonResult ok(Object data) {
        return build(200, "ok", data);
    }
}
