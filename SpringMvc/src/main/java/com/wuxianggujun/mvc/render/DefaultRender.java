package com.wuxianggujun.mvc.render;

import com.wuxianggujun.mvc.RequestHandlerChain;

/**
 * Ĭ����Ⱦ200
 */
public class DefaultRender implements Render{
    @Override
    public void render(RequestHandlerChain handlerChain) throws Exception {
        int status = handlerChain.getResponseStatus();
        handlerChain.getResponse().setStatus(status);
    }
}
