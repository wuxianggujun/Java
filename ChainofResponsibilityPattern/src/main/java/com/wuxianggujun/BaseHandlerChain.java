package com.wuxianggujun;

import lombok.Getter;

import java.util.List;

public class BaseHandlerChain<Handler extends BaseHandler<Param, Result>, Param, Result> {
    @Getter
    private final List<Handler> handlerList;

    public BaseHandlerChain(List<Handler> handlerList) {
        this.handlerList = handlerList;
    }


    public Result handleChain(Param param) {
        for (Handler handler : handlerList) {
            if (!handler.isHandler(param)) {
                continue;
            }
            HandleResult<Result> result = handler.doHandle(param);
            if (result.isNext()) {
                continue;
            }
            return result.getData();
        }
        return null;
    }

}
