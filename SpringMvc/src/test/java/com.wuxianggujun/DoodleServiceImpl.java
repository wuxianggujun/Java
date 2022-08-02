package com.wuxianggujun;

import com.wuxianggujun.core.annotation.Service;

@Service
public class DoodleServiceImpl implements DoodleService{
    @Override
    public String helloWord() {
        return "Hello word";
    }
}
