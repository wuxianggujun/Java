package com.wuxianggujun.muxin.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**继承自己的mapper
 * @param <T> 
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
