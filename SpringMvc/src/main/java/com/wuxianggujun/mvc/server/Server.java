package com.wuxianggujun.mvc.server;

/**
 * 服务器接口
 */
public interface Server {
    /**
     * 启动服务器
     * @throws Exception
     */
    void startServer() throws Exception;

    /**
     * 停止服务器
     * @throws Exception
     */
    void stopServer() throws Exception;
}

