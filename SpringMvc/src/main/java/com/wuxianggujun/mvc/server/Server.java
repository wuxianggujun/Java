package com.wuxianggujun.mvc.server;

/**
 * �������ӿ�
 */
public interface Server {
    /**
     * ����������
     * @throws Exception
     */
    void startServer() throws Exception;

    /**
     * ֹͣ������
     * @throws Exception
     */
    void stopServer() throws Exception;
}

