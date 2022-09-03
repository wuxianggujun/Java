package com.wuxianggujun.muxin.service.impl;

import com.google.zxing.WriterException;
import com.wuxianggujun.muxin.mapper.UsersMapper;
import com.wuxianggujun.muxin.pojo.Users;
import com.wuxianggujun.muxin.service.UserService;
import com.wuxianggujun.muxin.utils.QRCodeUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    private UsersMapper usersMapper;

    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Users user = new Users();
        user.setUsername(username);
        Users result = usersMapper.selectOne(user);
        return result != null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", password);
        Users result = usersMapper.selectOneByExample(userExample);
        return result;
    }

    @Override
    public Users saveUser(Users user) {
        String userID = sid.nextShort();

        //TODO 为每个用户生成一个唯一的二维码
        String qrCodePath = "C:\\Users\\MI\\IdeaProjects\\Java\\wuxiang-muxin-netty\\logs"+userID+"qrcode.png";
        try {
            QRCodeUtils.createQRCode("muxin_qrcode:"+user.getUsername(),qrCodePath,300,300);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("二维码创建错误！",e);
        }
        //将二维码上传到文件服务器
        //muxin——qrcode:[username]
        user.setQrcode("");
        user.setId(userID);
        usersMapper.insert(user);
        return user;
    }

    @Autowired
    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Autowired
    public void setSid(Sid sid) {
        this.sid = sid;
    }
}
