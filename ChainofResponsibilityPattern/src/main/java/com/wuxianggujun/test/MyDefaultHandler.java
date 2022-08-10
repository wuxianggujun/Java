package com.wuxianggujun.test;

import com.wuxianggujun.HandleResult;

public class MyDefaultHandler implements MyHandler {
    @Override
    public HandleResult<String> doHandle(String params) {
        System.out.println(String.format("MyDefaultHandler hello %s !", params));
        return HandleResult.doCurrentResult("MyDefaultHandler");
    }
}
