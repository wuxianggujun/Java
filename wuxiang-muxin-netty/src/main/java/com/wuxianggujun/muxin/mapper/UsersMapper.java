package com.wuxianggujun.muxin.mapper;

import com.wuxianggujun.muxin.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
* @author MI
* @description 针对表【users】的数据库操作Mapper
* @createDate 2022-09-02 18:54:00
* @Entity com.wuxianggujun.muxin.pojo.Users
*/
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}




