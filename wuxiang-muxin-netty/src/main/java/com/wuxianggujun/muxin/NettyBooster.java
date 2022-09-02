package com.wuxianggujun.muxin;

import com.wuxianggujun.muxin.netty.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyBooster implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            try {
                WSServer.getInstance().start();
            } catch (Exception e) {
                throw new RuntimeException("Websocket 初始化失败！",e);
            }
        }
    }


}
