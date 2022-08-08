package com.wuxianggujun.mvc.render;

import com.wuxianggujun.mvc.RequestHandlerChain;

/**
 * 渲染请求结果 interface
 */
public interface Render {
    /**
     * 执行渲染
     *
     * @param handlerChain
     * @throws Exception
     */
    void render(RequestHandlerChain handlerChain) throws Exception;
}
