package com.wuxianggujun;

import com.wuxianggujun.core.BeanContainer;
import com.wuxianggujun.ioc.Ioc;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class IocTest {

    @Test
    public void doIoc() throws Exception{
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.wuxianggujun");
        new Ioc().doIoc();
        DoodleController controller = (DoodleController) beanContainer.getBean(DoodleController.class);
        controller.hello();
    }
}
