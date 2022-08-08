package com.wuxianggujun.mvc.handler;

import com.wuxianggujun.mvc.RequestHandlerChain;
import lombok.extern.slf4j.Slf4j;

/**
 * 请求预处理
 */
@Slf4j
public class PreRequestHandler implements Handler {

    @Override
    public boolean handle(RequestHandlerChain handlerChain) throws Exception {
        //设置请求编码方式
        handlerChain.getRequest().setCharacterEncoding("UTF-8");
        String requestPath = handlerChain.getRequestPath();
        if (requestPath.length() > 1 && requestPath.endsWith("/")) {
            handlerChain.setRequestPath(requestPath.substring(0, requestPath.length() - 1));
        }
        log.info("[Doodle] {} {}", handlerChain.getRequestMethod(), handlerChain.getRequestPath());
        return true;
    }
}
