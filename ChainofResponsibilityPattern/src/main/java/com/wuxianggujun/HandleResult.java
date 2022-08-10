package com.wuxianggujun;

import lombok.Getter;

@Getter 
public class HandleResult<R>{
    private final R data;
    private final boolean next;

    public HandleResult(R data, boolean next) {
        this.data = data;
        this.next = next;
    }
    
    public static <R> HandleResult<R> doNextResult(){
        return new HandleResult<>(null,true);
    }
    
    public static <R> HandleResult<R> doCurrentResult(R r){
        return new HandleResult<>(r,false);
    }
}
