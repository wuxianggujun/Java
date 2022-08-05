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
 * Controller �ַ���
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
     * �����Ϣ�� ControllerMap��
     * @param clazz
     */
    private void putPathController(Class<?> clazz) {
        RequestMapping controllerRequest = clazz.getAnnotation(RequestMapping.class);
        String basePath = controllerRequest.value();
        Method[] controllerMethods = clazz.getDeclaredMethods();
        //���� Controller �еķ���
        for (Method method : controllerMethods) {
            if (method.isAnnotationPresent(RequestMapping.class)){
                //��ȡ��������Ĳ������ֺͲ�������
                Map<String,Class<?>> params = new HashMap<>();
                for (Parameter methodParam:method.getParameters()){
                    RequestParam requestParam = methodParam.getAnnotation(RequestParam.class);
                    if (requestParam == null) {
                        throw new RuntimeException("������ RequestParam ָ���Ĳ�����");
                    }
                    params.put(requestParam.value(),methodParam.getType());
                }
                //��ȡ��������ϵ�RequestMappingע��
                RequestMapping methodRequest = method.getAnnotation(RequestMapping.class);
                String methodPath = methodRequest.value();
                RequestMethod requestMethod = methodRequest.method();
                PathInfo pathInfo = new PathInfo(requestMethod.toString(),basePath+methodPath);
                if (pathControllerMap.containsKey(pathInfo)){
                    log.error("url:{} �ظ�ע��",pathInfo.getHttpPath());
                    throw new RuntimeException("url �ظ�ע��");
                }
                //���� ControllerInfo ������Map ��
                ControllerInfo controllerInfo =new ControllerInfo(clazz,method,params);
                this.pathControllerMap.put(pathInfo,controllerInfo);
                log.info("Add Controller RequestMethod:{}, RequestPath:{}, Controller:{}, Method:{}",
                        pathInfo.getHttpMethod(), pathInfo.getHttpPath(),
                        controllerInfo.getControllerClass().getName(), controllerInfo.getInvokeMethod().getName());
            }
        }
    }
}
