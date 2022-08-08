package com.wuxianggujun.mvc;

import com.wuxianggujun.mvc.handler.Handler;
import com.wuxianggujun.mvc.render.DefaultRender;
import com.wuxianggujun.mvc.render.InternalErrorRender;
import com.wuxianggujun.mvc.render.Render;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

/**
 * http 请求处理链
 */
@Data
@Slf4j
public class RequestHandlerChain {

    /**
     * Handler 迭代器
     */
    private Iterator<Handler> handlerIt;

    /**
     * 请求 request
     */
    private HttpServletRequest request;

    /**
     * 请求 response
     */
    private HttpServletResponse response;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求 http 路径
     */
    private String requestPath;

    /**
     * 请求状态码
     */
    private int responseStatus;

    /**
     * 请求结果处理器
     */
    private Render render;

    public RequestHandlerChain(Iterator<Handler> handlerIt, HttpServletRequest request, HttpServletResponse response) {
        this.handlerIt = handlerIt;
        this.request = request;
        this.response = response;
        this.requestMethod = request.getMethod();
        this.requestPath = request.getPathInfo();
        this.responseStatus = HttpServletResponse.SC_OK;
    }

    /**
     * 执行请求链
     */
    public void doHandlerChain(){
        try {
            while (handlerIt.hasNext()){
                if (!handlerIt.next().handle(this)){
                    break;
                }
            }
        }catch (Exception e){
            log.error("doHandlerChain error:",e);
            render = new InternalErrorRender();
        }
    }
    
    public void doRender(){
        if (null == render){
            render = new DefaultRender();
        }
        try {
            render.render(this);
        }catch (Exception e){
            log.error("doRender error:",e);
            throw new RuntimeException(e);
        }
    }
}
