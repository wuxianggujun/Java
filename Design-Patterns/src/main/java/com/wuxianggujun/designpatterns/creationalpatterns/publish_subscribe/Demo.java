package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe;

public class Demo {
    public static void main(String[] args) {
        WeChatServer weChatServer = new WeChatServer();
        Observer zhangSan = new UserObserverable("ZhangSan");
        Observer liSi = new UserObserverable("LiSi");
        Observer wangWu = new UserObserverable("WangWu");
        weChatServer.registerObserver(liSi);
        weChatServer.registerObserver(wangWu);
        weChatServer.registerObserver(zhangSan);
        weChatServer.setMessage("PHP是世界上最好的语言！");
        
        weChatServer.cancelObserver(liSi); //这里取消订阅
        weChatServer.setMessage("flutter 重磅来袭！！");
        
    }
}
