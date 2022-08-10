package com.wuxianggujun.test;

import com.wuxianggujun.HandleResult;

public class MyLogHandler implements MyHandler {
    @Override
    public HandleResult<String> doHandle(String params) {
        System.out.println(String.format("MyLogHandler hello %s !", params));
        return HandleResult.doNextResult();
    }
}
