package com.sample;

import com.wuxianggujun.core.annotation.Controller;
import com.wuxianggujun.mvc.annotation.RequestMapping;
import com.wuxianggujun.mvc.annotation.ResponseBody;

@Controller
@RequestMapping
public class DoodleController {

    @RequestMapping
    @ResponseBody
    public String hello(){
        return "hello doodle";
    }
}
