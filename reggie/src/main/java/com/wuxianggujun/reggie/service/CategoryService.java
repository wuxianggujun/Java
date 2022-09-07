package com.wuxianggujun.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuxianggujun.reggie.entity.Category;

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
