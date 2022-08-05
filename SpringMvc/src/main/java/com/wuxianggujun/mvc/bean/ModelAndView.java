package com.wuxianggujun.mvc.bean;


import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    /**
     * 页面路径
     */
    private String view;

    public Map<String, Object> getModel() {
        return model;
    }

    /**
     * 页面data数据
     */
    private final Map<String,Object> model = new HashMap<>();

    public String getView() {
        return view;
    }

    public ModelAndView setView(String view) {
        this.view = view;
        return this;
    }
    
    public  ModelAndView addObject(String attributeName,Object attributeValue) {
        model.put(attributeName,attributeValue);
        return this;
    }
    
    public ModelAndView addAllObjects(Map<String,?> modelMap) {
        model.putAll(modelMap);
        return this;
    }
    
}
