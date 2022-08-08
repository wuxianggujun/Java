package com.wuxianggujun.mvc.handler;

import com.wuxianggujun.core.BeanContainer;
import com.wuxianggujun.mvc.ControllerInfo;
import com.wuxianggujun.mvc.PathInfo;
import com.wuxianggujun.mvc.RequestHandlerChain;
import com.wuxianggujun.mvc.annotation.RequestMapping;
import com.wuxianggujun.mvc.annotation.RequestParam;
import com.wuxianggujun.mvc.annotation.ResponseBody;
import com.wuxianggujun.mvc.render.Render;
import com.wuxianggujun.util.CastUtil;
import com.wuxianggujun.util.ValidateUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class ControllerHandler implements Handler {

    /**
     * 请求信息和 controller 信息关系 map
     */
    private Map<PathInfo, ControllerInfo> pathControllerMap = new ConcurrentHashMap<>();

    /**
     * bean 容器
     */
    private BeanContainer beanContainer;

    public ControllerHandler() {
        beanContainer = BeanContainer.getInstance();
        Set<Class<?>> mappingSet = beanContainer.getClassesByAnnotation(RequestMapping.class);
        this.initPathControllerMap(mappingSet);

    }

    private void initPathControllerMap(Set<Class<?>> mappingSet) {
        mappingSet.forEach(this::addPathController);
    }

    /**
     * 初始化 pathControllerMap
     *
     * @param clazz
     */
    private void addPathController(Class<?> clazz) {
        RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
        String basePath = requestMapping.value();
        if (!basePath.startsWith("/")) {
            basePath = "/" + basePath;
        }
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping methodRequest = method.getAnnotation(RequestMapping.class);
                String methodPath = methodRequest.value();
                if (!methodPath.startsWith("/")) {
                    methodPath = "/" + methodPath;
                }
                String url = basePath + methodPath;
                Map<String, Class<?>> methodParams = this.getMethodParams(method);
                String httpMethod = String.valueOf(methodRequest.method());
                PathInfo pathInfo = new PathInfo(httpMethod, url);
                if (pathControllerMap.containsKey(pathInfo)) {
                    log.warn("url:{} 重复注册", pathInfo.getHttpPath());
                }
                ControllerInfo controllerInfo = new ControllerInfo(clazz, method, methodParams);
                this.pathControllerMap.put(pathInfo, controllerInfo);
                log.info("mapped:[{},method=[{}]] controller:[{}@{}]",
                        pathInfo.getHttpPath(), pathInfo.getHttpMethod(),
                        controllerInfo.getControllerClass().getName(), controllerInfo.getInvokeMethod().getName());
            }
        }
    }

    /**
     * 获取执行方法的参数
     *
     * @param method
     * @return
     */
    private Map<String, Class<?>> getMethodParams(Method method) {
        Map<String, Class<?>> map = new HashMap<>();
        for (Parameter parameter : method.getParameters()) {
            RequestParam param = parameter.getAnnotation(RequestParam.class);
            //TODO:不使用注解匹配参数名字
            if (param == null) {
                throw new RuntimeException("必须有 RequestParam 指定的参数名");
            }
            map.put(param.value(), parameter.getType());
        }
        return null;
    }

    @Override
    public boolean handle(RequestHandlerChain handlerChain) throws Exception {
        String method = handlerChain.getRequestMethod();
        String path = handlerChain.getRequestPath();
        ControllerInfo controllerInfo = pathControllerMap.get(new PathInfo(method, path));
        if (controllerInfo == null) {
            handlerChain.setRender(new NotFoundRender());
            return false;
        }
        Object result = invokeController(controllerInfo, handlerChain.getRequest());
        setRender(result, controllerInfo, handlerChain);
        return true;
    }


    /**
     * 设置请求结果执行器
     *
     * @param result
     * @param controllerInfo
     * @param handlerChain
     */
    private void setRender(Object result, ControllerInfo controllerInfo, RequestHandlerChain handlerChain) {
        if (result == null) {
            return;
        }
        Render render;
        boolean isJson = controllerInfo.getInvokeMethod().isAnnotationPresent(ResponseBody.class);
        if (isJson) {
            render = new JsonRender(result);
        } else {
            render = new ViewRender(result);
        }
        handlerChain.setRender(render);
    }

    private Object invokeController(ControllerInfo controllerInfo, HttpServletRequest request) {
        Map<String, String> requestParams = getRequestParams(request);
        List<Object> methodParams = instantiateMethodArgs(controllerInfo.getMethodParameter(), requestParams);

        Object controller = beanContainer.getBean(controllerInfo.getControllerClass());
        Method invokeMethod = controllerInfo.getInvokeMethod();
        invokeMethod.setAccessible(true);
        Object result;
        try {
            if (methodParams.size() == 0) {
                result = invokeMethod.invoke(controller);
            } else {
                result = invokeMethod.invoke(controller, methodParams.toArray());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<String, String>();
        //GET 和 POST 方法是这样子获取请求参数的
        request.getParameterMap().forEach((paramName, paramsValues) -> {
            if (ValidateUtil.isNotEmpty(paramsValues)) {
                paramMap.put(paramName, paramsValues[0]);
            }
        });
        //TODO: Body，Path，Header 等方式的请求参数类型
        return paramMap;
    }

    /**
     * 实例化方法参数
     *
     * @param requestParams
     * @return
     */
    private List<Object> instantiateMethodArgs(Map<String, Class<?>> methodParams, Map<String, String> requestParams) {
        return methodParams.keySet().stream().map(paramName -> {
            Class<?> type = methodParams.get(paramName);
            String requestValue = requestParams.get(paramName);
            Object value;
            if (requestValue == null) {
                value = CastUtil.primitiveNull(type);
            } else {
                value = CastUtil.convert(type, requestValue);
                //TODO:实现非原生类的参数实例化

            }
            return value;
        }).collect(Collectors.toList());
    }

}
