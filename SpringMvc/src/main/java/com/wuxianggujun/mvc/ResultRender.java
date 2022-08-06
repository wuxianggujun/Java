package com.wuxianggujun.mvc;

import com.alibaba.fastjson.JSON;
import com.wuxianggujun.Doodle;
import com.wuxianggujun.core.BeanContainer;
import com.wuxianggujun.mvc.annotation.ResponseBody;
import com.wuxianggujun.mvc.bean.ModelAndView;
import com.wuxianggujun.util.CastUtil;
import com.wuxianggujun.util.ValidateUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 结果执行器
 */
@Slf4j
public class ResultRender {

    private final BeanContainer beanContainer;

    public ResultRender() {
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 执行Controller方法
     */
    public void invokeController(HttpServletRequest req, HttpServletResponse resp, ControllerInfo controllerInfo) {
        //获取 HttpServletRequest 所有参数
        Map<String, String> requestParam = getRequestParams(req);
        //实例化调用方法要传入的参数值
        List<Object> methodParams = instantiateMethodArgs(controllerInfo.getMethodParameter(), requestParam);

        Object controller = beanContainer.getBean(controllerInfo.getControllerClass());
        Method invokeMethod = controllerInfo.getInvokeMethod();
        invokeMethod.setAccessible(true);
        Object result;
        //通过反射调用此方法
        try {
            if (methodParams.size() == 0) {
                result = invokeMethod.invoke(controller);
            } else {
                result = invokeMethod.invoke(controller, methodParams.toArray());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //解析方法返回值，选择返回页面或者json
        resultResolver(controllerInfo, result, req, resp);
    }

    /**
     * 方法执行后返回值解析
     *
     * @param controllerInfo
     * @param result
     * @param req
     * @param resp
     */
    private void resultResolver(ControllerInfo controllerInfo, Object result, HttpServletRequest req, HttpServletResponse resp) {
        if (result == null) {
            return;
        }
        boolean isJson = controllerInfo.getInvokeMethod().isAnnotationPresent(ResponseBody.class);
        if (isJson) {
            //设置响应头
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            //向响应中写入数据
            try (PrintWriter writer = resp.getWriter()) {
                writer.write(JSON.toJSONString(result));
                writer.flush();
            } catch (IOException e) {
                log.error("转发请求失败", e);
            }
        } else {
            String path;
            if (result instanceof ModelAndView) {
                ModelAndView mv = (ModelAndView) result;
                path = mv.getView();
                Map<String, Object> model = mv.getModel();
                if (ValidateUtil.isNotEmpty(model)) {
                    for (Map.Entry<String, Object> entry : model.entrySet()) {
                        req.setAttribute(entry.getKey(), entry.getValue());
                    }
                }
            } else if (result instanceof String) {
                path = (String) result;
            } else {
                throw new RuntimeException("返回值类型不合法");
            }
            try {
                Doodle.getConfiguration().getResourcePath();
                //req.getRequestDispatcher("/templates/" + path).forward(req, resp);
                req.getRequestDispatcher(Doodle.getConfiguration().getResourcePath()+path).forward(req,resp);
            } catch (Exception e) {
                log.error("转发请求失败", e);
                //TODO:异常统一处理，400等
            }
        }

    }

    private List<Object> instantiateMethodArgs(Map<String, Class<?>> methodParams, Map<String, String> requestParams) {
        return methodParams.keySet().stream().map(paramName -> {
            Class<?> type = methodParams.get(paramName);
            String requestValue = requestParams.get(paramName);
            Object value;
            if (requestValue == null) {
                value = CastUtil.primitiveNull(type);
            } else {
                value = CastUtil.convert(type, requestValue);
            }
            return value;
        }).collect(Collectors.toList());
    }

    /**
     * 获取 http 中的参数
     */
    private Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        //GET 和 POST 方法是这样请求参数的
        request.getParameterMap().forEach((paramName, paramsValues) -> {
            if (ValidateUtil.isNotEmpty(paramsValues)) {
                paramMap.put(paramName, paramsValues[0]);
            }
        });
        // TODO: Body、Path、Header 等方式的请求参数获取
        return paramMap;
    }

}
