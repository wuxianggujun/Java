package com.wuxianggujun.mvc.handler;

import com.alibaba.fastjson.JSON;
import com.wuxianggujun.mvc.RequestHandlerChain;
import com.wuxianggujun.mvc.render.Render;

import java.io.PrintWriter;

/**
 * ��Ⱦjson
 */
public class JsonRender implements Render {
    
    private Object jsonData;
    
    
    public JsonRender(Object result) {
        this.jsonData = result;
    }

    @Override
    public void render(RequestHandlerChain handlerChain) throws Exception {
        //������Ӧͷ
        handlerChain.getResponse().setContentType("application/json");
        handlerChain.getResponse().setCharacterEncoding("UTF-8");
        //����Ӧ��д������
        try (PrintWriter writer = handlerChain.getResponse().getWriter()){
            writer.write(JSON.toJSONString(jsonData));
            writer.flush();
        }
    }
}
