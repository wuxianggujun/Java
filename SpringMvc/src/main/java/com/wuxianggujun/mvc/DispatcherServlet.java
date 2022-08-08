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
 * DispatcherServlet ���е�http �����ɴ� Servlet ת��
 */
@Slf4j
public class DispatcherServlet extends HttpServlet {
    private ControllerHandler controllerHandler = new ControllerHandler();
    private ResultRender resultRender = new ResultRender();
    /**
     * ����ִ����
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
     * ִ������
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
