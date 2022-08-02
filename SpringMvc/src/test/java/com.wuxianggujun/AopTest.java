package com.wuxianggujun;

import com.wuxianggujun.aop.Aop;
import com.wuxianggujun.core.BeanContainer;
import com.wuxianggujun.ioc.Ioc;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class AopTest {
    
    @Test
    public void doApp() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.wuxianggujun");
        new Aop().doAop();
        new Ioc().doIoc();
        DoodleController controller = (DoodleController) beanContainer.getBean(DoodleController.class);
        controller.hello();
    }
}
