package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe;

public interface Observer {
    /***
     * ����һ��update ����,�����۲���<΢�Ź��ں�> �����¼���ʱ��,��ִ�и÷���.
     * @param message
     */
    void update(String message);
}
