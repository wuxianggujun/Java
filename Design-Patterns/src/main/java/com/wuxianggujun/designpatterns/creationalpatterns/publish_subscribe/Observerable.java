package com.wuxianggujun.designpatterns.creationalpatterns.publish_subscribe;

public interface Observerable {

    /**
     * ע���¼�
     */
    void registerObserver(Observer observer);

    /**
     * ȡ���¼�
     */
    void cancelObserver(Observer observer);


    /**
     * �����¼� ����һ��֪ͨ
     */
    void notifyObserver();
    
}
