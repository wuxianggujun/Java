package com.wuxianggujun.mvc.handler;

import com.wuxianggujun.mvc.RequestHandlerChain;

/**
 * ����ִ���� Handler
 */
public interface Handler {
    /**
     * 
     */
    boolean handle(final RequestHandlerChain handlerChain) throws Exception;
}
