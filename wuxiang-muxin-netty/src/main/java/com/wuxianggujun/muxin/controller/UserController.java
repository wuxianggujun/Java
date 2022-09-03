package com.wuxianggujun.muxin.controller;

import com.wuxianggujun.muxin.pojo.Users;
import com.wuxianggujun.muxin.pojo.vo.UsersVo;
import com.wuxianggujun.muxin.service.UserService;
import com.wuxianggujun.muxin.utils.JsonResult;
import com.wuxianggujun.muxin.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("u")
public class UserController {

    private UserService userService;

    @PostMapping("/registerOrLogin")
    public JsonResult registerOrLogin(@RequestBody Users user) throws Exception {
        //判断用户名和密码不能为null
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return JsonResult.errorMsg("用户名或密码不能为空！");
        }
        //判断用户名是否存在，如果存在就登录，如果不存在就注册
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        Users userResult = null;
        if (usernameIsExist) {
            //登录
           userResult = userService.queryUserForLogin(user.getUsername(),MD5Utils.string2MD5(user.getPassword()));
            if (userResult == null) {
                return JsonResult.errorMsg("用户名或密码不正确！");
            }
        } else {
            //注册
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.string2MD5(user.getPassword()));
            userResult = userService.saveUser(user);
        }
        UsersVo usersVo = new UsersVo();
        BeanUtils.copyProperties(user,usersVo);
        return JsonResult.ok(usersVo);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
