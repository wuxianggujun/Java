package com.wuxianggujun.mvc;

import com.wuxianggujun.core.BeanContainer;
import com.wuxianggujun.mvc.annotation.RequestMapping;
import com.wuxianggujun.mvc.annotation.RequestMethod;
import com.wuxianggujun.mvc.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Controller 分发器
 */
@Slf4j
public class ControllerHandler {

    private Map<PathInfo,ControllerInfo> pathControllerMap = new ConcurrentHashMap<>();
    
    private BeanContainer beanContainer;


    public ControllerHandler() {
        beanContainer = BeanContainer.getInstance();
        Set<Class<?>> classSet = beanContainer.getClassesByAnnotation(RequestMapping.class);
        for (Class<?> clazz : classSet) {
            putPathController(clazz);
        }
    }

    public ControllerInfo getController(String requestMethod,String requestPath){
       PathInfo pathInfo = new PathInfo(requestMethod,requestPath);
       return pathControllerMap.get(pathInfo);
    }

    /**
     * 添加信息到 ControllerMap中
     * @param clazz
     */
    private void putPathController(Class<?> clazz) {
        RequestMapping controllerRequest = clazz.getAnnotation(RequestMapping.class);
        String basePath = controllerRequest.value();
        Method[] controllerMethods = clazz.getDeclaredMethods();
        //遍历 Controller 中的方法
        for (Method method : controllerMethods) {
            if (method.isAnnotationPresent(RequestMapping.class)){
                //获取这个方法的参数名字和参数类型
                Map<String,Class<?>> params = new HashMap<>();
                for (Parameter methodParam:method.getParameters()){
                    RequestParam requestParam = methodParam.getAnnotation(RequestParam.class);
                    if (requestParam == null) {
                        throw new RuntimeException("必须有 RequestParam 指定的参数名");
                    }
                    params.put(requestParam.value(),methodParam.getType());
                }
                //获取这个方法上的RequestMapping注解
                RequestMapping methodRequest = method.getAnnotation(RequestMapping.class);
                String methodPath = methodRequest.value();
                RequestMethod requestMethod = methodRequest.method();
                PathInfo pathInfo = new PathInfo(requestMethod.toString(),basePath+methodPath);
                if (pathControllerMap.containsKey(pathInfo)){
                    log.error("url:{} 重复注册",pathInfo.getHttpPath());
                    throw new RuntimeException("url 重复注册");
                }
                //生成 ControllerInfo 并存入Map 中
                ControllerInfo controllerInfo =new ControllerInfo(clazz,method,params);
                this.pathControllerMap.put(pathInfo,controllerInfo);
                log.info("Add Controller RequestMethod:{}, RequestPath:{}, Controller:{}, Method:{}",
                        pathInfo.getHttpMethod(), pathInfo.getHttpPath(),
                        controllerInfo.getControllerClass().getName(), controllerInfo.getInvokeMethod().getName());
            }
        }
    }
}
