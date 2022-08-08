package com.wuxianggujun.mvc.handler;

import com.wuxianggujun.Doodle;
import com.wuxianggujun.mvc.RequestHandlerChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 普通Url请求执行
 * 主要处理静态资源
 */
@Slf4j
public class SimpleUrlHandler implements Handler {

    /**
     * tomcat 默认 RequestDispatcher 的名称
     * TODO: 其他服务器默认的RequestDispatcher，如WebLogic为FileServlet
     */
    private static final String TOMCAT_DEFAULT_SERVLET = "default";

    /**
     * 默认的 RequestDispatcher 处理静态资源
     */
    private RequestDispatcher defaultServlet;
    
    public SimpleUrlHandler(ServletContext servletContext){
        defaultServlet = servletContext.getNamedDispatcher(TOMCAT_DEFAULT_SERVLET);
        if (defaultServlet == null) {
            throw new RuntimeException("没有默认的 Servlet");
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
     * 是否为静态资源
     * @param Url
     * @return
     */
    private  boolean isStaticResource(String Url){
        return Url.startsWith(Doodle.getConfiguration().getAssetPath());
    }
}
