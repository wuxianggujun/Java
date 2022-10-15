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
        weChatServer.setMessage("PHP����������õ����ԣ�");
        
        weChatServer.cancelObserver(liSi); //����ȡ������
        weChatServer.setMessage("flutter �ذ���Ϯ����");
        
    }
}
