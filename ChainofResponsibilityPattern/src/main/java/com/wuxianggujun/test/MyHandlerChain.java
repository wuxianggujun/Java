package com.wuxianggujun.test;

import com.wuxianggujun.BaseHandlerChain;

import java.util.List;

public class MyHandlerChain extends BaseHandlerChain<MyHandler, String, String> {
    public MyHandlerChain(List<MyHandler> myHandlers) {
        super(myHandlers);
    }
}
