package com.wuxianggujun.mvc.handler;

import com.wuxianggujun.mvc.RequestHandlerChain;

/**
 * ÇëÇóÖ´ÐÐÆ÷ Handler
 */
public interface Handler {
    /**
     * 
     */
    boolean handle(final RequestHandlerChain handlerChain) throws Exception;
}
