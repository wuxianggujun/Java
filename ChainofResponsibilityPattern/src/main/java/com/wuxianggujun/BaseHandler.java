package com.wuxianggujun;

/**
 * 通用责任链的Handler
 * @param <Param>
 * @param <Result>
 *     每个业务声明一个该接口的子接口或者抽象类，再基于该接口实现对应的业务 Handler
 *     这样BaseHandlerChain 可以直接注入到对应的Handler List
 */
public interface BaseHandler<Param,Result> {

    
    HandleResult doHandle(Param params);
    
    default boolean isHandler(Param param){
        return true;
    }
}
