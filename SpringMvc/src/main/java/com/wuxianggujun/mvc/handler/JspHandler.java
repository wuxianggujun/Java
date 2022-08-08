package com.wuxianggujun.mvc.handler;

import com.wuxianggujun.Doodle;
import com.wuxianggujun.mvc.RequestHandlerChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;

/**
 * Jsp ������
 * ��Ҫ���� jsp ��Դ����
 */
public class JspHandler implements Handler {

    /**
     * jsp ���� RequestDispatcher ������
     */
    private static  final String JSP_SERVLET = "jsp";


    /**
     * jsp ��RequestDispatcher������jsp��Դ
     */
    private RequestDispatcher jspServlet;
    
    public JspHandler(ServletContext servletContext){
        jspServlet = servletContext.getNamedDispatcher(JSP_SERVLET);
        if (jspServlet == null) {
            throw new RuntimeException("û�� jsp Servlet")
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
     * �Ƿ�Ϊjsp ��Դ
     * @param url
     * @return
     */
    private boolean isPageView(String url){
        return url.startsWith(Doodle.getConfiguration().getViewPath());
    }
}
