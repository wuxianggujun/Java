package com.wuxianggujun.mvc.render;

import com.wuxianggujun.mvc.RequestHandlerChain;

/**
 * ��Ⱦ������ interface
 */
public interface Render {
    /**
     * ִ����Ⱦ
     *
     * @param handlerChain
     * @throws Exception
     */
    void render(RequestHandlerChain handlerChain) throws Exception;
}
