package com.wuxianggujun.mvc;

import com.wuxianggujun.mvc.handler.Handler;
import com.wuxianggujun.mvc.handler.JspHandler;
import com.wuxianggujun.mvc.handler.PreRequestHandler;
import com.wuxianggujun.mvc.handler.SimpleUrlHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DispatcherServlet 所有的http 请求都由此 Servlet 转发
 */
@Slf4j
public class DispatcherServlet extends HttpServlet {
    private ControllerHandler controllerHandler = new ControllerHandler();
    private ResultRender resultRender = new ResultRender();
    /**
     * 请求执行器
     */
    private final List<Handler> HANDLER = new ArrayList<>();


    @Override
    public void init() throws ServletException {
        HANDLER.add(new PreRequestHandler());
        HANDLER.add(new SimpleUrlHandler(getServletContext()));
        HANDLER.add(new JspHandler(getServletContext()));
        HANDLER.add(new com.wuxianggujun.mvc.handler.ControllerHandler());
    }

    /**
     * 执行请求
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestHandlerChain handlerChain = new RequestHandlerChain(HANDLER.iterator(), req, resp);
        handlerChain.doHandlerChain();
        handlerChain.doRender();
    }
}
