package com.wuxianggujun.mvc.handler;

import com.wuxianggujun.Doodle;
import com.wuxianggujun.mvc.RequestHandlerChain;
import com.wuxianggujun.mvc.bean.ModelAndView;
import com.wuxianggujun.mvc.render.Render;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * 渲染页面
 */
public class ViewRender implements Render {
    private ModelAndView mv;
    
    public ViewRender(Object result) {
        if (result instanceof ModelAndView) {
            this.mv = (ModelAndView) result;
        }else if (result instanceof String){
            this.mv = new ModelAndView().setView((String) result);
        }else {
            throw new RuntimeException("返回类型不合法");
        }
    }

    @Override
    public void render(RequestHandlerChain handlerChain) throws Exception {
        HttpServletRequest request = handlerChain.getRequest();
        HttpServletResponse response = handlerChain.getResponse();
        String path = mv.getView();
        Map<String,Object> model = mv.getModel();
        model.forEach(request::setAttribute);
        request.getRequestDispatcher(Doodle.getConfiguration().getViewPath()+path).forward(request,response);
        
    }
}
