package com.wuxianggujun.mvc;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * DispatcherServlet 所有的http 请求都由此 Servlet 转发
 */
@Slf4j
public class DispatcherServlet extends HttpServlet {
    private ControllerHandler controllerHandler = new ControllerHandler();
    private ResultRender resultRender = new ResultRender();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码请求
        req.setCharacterEncoding("UTF-8");
        //获取请求方法和请求路径
        String requestMethod = req.getMethod();
        String requestPath = req.getPathInfo();
        log.info("[DoodleConfig] {} {}", requestMethod, requestPath);
        if (requestPath.endsWith("/")){
            requestPath = requestPath.substring(0,requestPath.length()-1);
        }
        ControllerInfo controllerInfo = controllerHandler.getController(requestMethod,requestPath);
        log.info("{}",controllerInfo);
        if (controllerInfo == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        resultRender.invokeController(req,resp,controllerInfo);
    }
}
