package com.wuxianggujun.mvc.handler;

import com.wuxianggujun.mvc.RequestHandlerChain;
import com.wuxianggujun.mvc.render.Render;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ‰÷»æ404
 */

public class NotFoundRender implements Render {
    @Override
    public void render(RequestHandlerChain handlerChain) throws Exception {
        handlerChain.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
