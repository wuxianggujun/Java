package com.wuxianggujun.muxin.service;

import com.google.zxing.WriterException;
import com.wuxianggujun.muxin.pojo.Users;

import java.io.IOException;

public interface UserService {
    //判断用户名是否存在
    public boolean queryUsernameIsExist(String username);
    
    //查询用户是否存在
    Users queryUserForLogin(String username,String password);
    //用户注册
    Users saveUser(Users user) throws IOException, WriterException;
}
