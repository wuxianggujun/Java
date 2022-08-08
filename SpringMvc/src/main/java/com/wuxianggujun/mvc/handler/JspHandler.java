package com.wuxianggujun.mvc.handler;

import com.wuxianggujun.Doodle;
import com.wuxianggujun.mvc.RequestHandlerChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;

/**
 * Jsp 请求处理
 * 主要负责 jsp 资源请求
 */
public class JspHandler implements Handler {

    /**
     * jsp 请求 RequestDispatcher 的名称
     */
    private static  final String JSP_SERVLET = "jsp";


    /**
     * jsp 的RequestDispatcher，处理jsp资源
     */
    private RequestDispatcher jspServlet;
    
    public JspHandler(ServletContext servletContext){
        jspServlet = servletContext.getNamedDispatcher(JSP_SERVLET);
        if (jspServlet == null) {
            throw new RuntimeException("没有 jsp Servlet")
        }
    }
    
    
    @Override
    public boolean handle(RequestHandlerChain handlerChain) throws Exception {
        if (isPageView(handlerChain.getRequestPath())){
            jspServlet.forward(handlerChain.getRequest(),handlerChain.getResponse());
        return false;
        }
        return true;
    }

    /**
     * 是否为jsp 资源
     * @param url
     * @return
     */
    private boolean isPageView(String url){
        return url.startsWith(Doodle.getConfiguration().getViewPath());
    }
}
