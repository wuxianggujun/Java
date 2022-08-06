package com.wuxianggujun;

import com.wuxianggujun.aop.Aop;
import com.wuxianggujun.core.BeanContainer;
import com.wuxianggujun.ioc.Ioc;
import com.wuxianggujun.mvc.server.Server;
import com.wuxianggujun.mvc.server.TomcatServer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Doodle Starter
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class Doodle {

    /**
     * ȫ������
     */
    @Getter
    private static Configuration configuration = Configuration.builder().build();

    /**
     * Ĭ�Ϸ�����
     */
    @Getter
    private static Server server;

    public static void run(Class<?> bootClass) {
        run(Configuration.builder().bootClass(bootClass).build());
    }

    public static void run(Class<?> bootClass, int port) {
        run(Configuration.builder().bootClass(bootClass).serverPort(port).build());
    }

    public static void run(Configuration configuration) {
        new Doodle().start(configuration);
    }

    private void start(Configuration configuration) {
        try {
            Doodle.configuration = configuration;
            String basePackage = configuration.getBootClass().getPackage().getName();
            BeanContainer.getInstance().loadBeans(basePackage);
            //ע��Aop������ioc֮ǰִ��
            new Aop().doAop();
            new Ioc().doIoc();

            server = new TomcatServer(configuration);
            server.startServer();
        } catch (Exception e) {
            log.error("Doodle ����ʧ��", e);
        }
    }


}
