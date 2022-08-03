package com.wuxianggujun;

import com.wuxianggujun.core.annotation.Autowired;
import com.wuxianggujun.core.annotation.Controller;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DoodleController {
    @Autowired
    private DoodleService doodleService;

    public void hello() {
        log.info(doodleService.helloWord());
    }

    public void helloForAspect() {
        log.info("Hello Aspectj");
    }
}
