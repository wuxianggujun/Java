package com.wuxianggujun;

/**
 * ͨ����������Handler
 * @param <Param>
 * @param <Result>
 *     ÿ��ҵ������һ���ýӿڵ��ӽӿڻ��߳����࣬�ٻ��ڸýӿ�ʵ�ֶ�Ӧ��ҵ�� Handler
 *     ����BaseHandlerChain ����ֱ��ע�뵽��Ӧ��Handler List
 */
public interface BaseHandler<Param,Result> {

    
    HandleResult doHandle(Param params);
    
    default boolean isHandler(Param param){
        return true;
    }
}
