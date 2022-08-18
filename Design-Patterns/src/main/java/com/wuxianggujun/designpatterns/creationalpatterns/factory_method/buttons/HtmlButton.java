package com.wuxianggujun.designpatterns.creationalpatterns.factory_method.buttons;

/**
 * 具体产品
 */
public class HtmlButton implements Button {
    @Override
    public void render() {
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Click! Button says - Hello world!");
    }
}
