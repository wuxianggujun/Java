package com.wuxianggujun.mvc.handler;

import com.wuxianggujun.Doodle;
import com.wuxianggujun.mvc.RequestHandlerChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

/**
 * ��ͨUrl����ִ��
 * ��Ҫ����̬��Դ
 */
@Slf4j
public class SimpleUrlHandler implements Handler {

    /**
     * tomcat Ĭ�� RequestDispatcher ������
     * TODO: ����������Ĭ�ϵ�RequestDispatcher����WebLogicΪFileServlet
     */
    private static final String TOMCAT_DEFAULT_SERVLET = "default";

    /**
     * Ĭ�ϵ� RequestDispatcher ����̬��Դ
     */
    private RequestDispatcher defaultServlet;
    
    public SimpleUrlHandler(ServletContext servletContext){
        defaultServlet = servletContext.getNamedDispatcher(TOMCAT_DEFAULT_SERVLET);
        if (defaultServlet == null) {
            throw new RuntimeException("û��Ĭ�ϵ� Servlet");
        }
        log.info("The default servlet for serving static resource is [{}]", TOMCAT_DEFAULT_SERVLET);
    }
    @Override
    public boolean handle(RequestHandlerChain handlerChain) throws Exception {
        if (isStaticResource(handlerChain.getRequestPath())){
            defaultServlet.forward(handlerChain.getRequest(),handlerChain.getResponse());
            return false;
        }
        return true;
    }

    /**
     * �Ƿ�Ϊ��̬��Դ
     * @param Url
     * @return
     */
    private  boolean isStaticResource(String Url){
        return Url.startsWith(Doodle.getConfiguration().getAssetPath());
    }
}
