package com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.buttons;

public class MacOSButton implements Button{
    @Override
    public void paint() {
        System.out.println("您已经创建了 MacOSButton");
    }
}
