package com.wuxianggujun.test;

import java.util.ArrayList;
import java.util.List;

public class BaseHandlerChainTest {

    private static MyHandlerChain handlerChain;

    static {
        List<MyHandler> list = new ArrayList<MyHandler>();
        list.add(new MyDefaultHandler());
        list.add(new MyLogHandler());
        handlerChain = new MyHandlerChain(list);
    }

    public static void main(String[] args) {
        String result = handlerChain.handleChain("zzzzbw");
        System.out.println(result);
    }
}
