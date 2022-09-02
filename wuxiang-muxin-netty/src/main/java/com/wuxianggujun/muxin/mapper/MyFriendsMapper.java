package com.wuxianggujun.muxin.mapper;

import com.wuxianggujun.muxin.pojo.MyFriends;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
* @author MI
* @description 针对表【my_friends】的数据库操作Mapper
* @createDate 2022-09-02 18:54:00
* @Entity com.wuxianggujun.muxin.pojo.MyFriends
*/
@Mapper
public interface MyFriendsMapper extends BaseMapper<MyFriends> {

}




