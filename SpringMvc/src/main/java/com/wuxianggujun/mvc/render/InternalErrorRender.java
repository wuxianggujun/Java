package com.wuxianggujun.mvc.render;

import com.wuxianggujun.mvc.RequestHandlerChain;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ‰÷»æ500
 */
public class InternalErrorRender implements Render {
    @Override
    public void render(RequestHandlerChain handlerChain) throws Exception {
        handlerChain.getResponse().sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
