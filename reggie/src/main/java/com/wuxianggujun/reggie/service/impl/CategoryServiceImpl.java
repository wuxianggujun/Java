package com.wuxianggujun.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxianggujun.reggie.common.CustomException;
import com.wuxianggujun.reggie.entity.Category;
import com.wuxianggujun.reggie.entity.Dish;
import com.wuxianggujun.reggie.entity.Setmeal;
import com.wuxianggujun.reggie.mapper.CategoryMapper;
import com.wuxianggujun.reggie.service.CategoryService;
import com.wuxianggujun.reggie.service.DishService;
import com.wuxianggujun.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件,根据分类id查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        long count = dishService.count(dishLambdaQueryWrapper);
        if (count > 0) {
            // 已经关联菜品，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }
        // 查询当前分类是否关联了菜品,如果关联抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        long count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) {
            // 已经关联套餐，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        // 查询当前分类是否关联了套餐,如果关联抛出一个业务异常

        // 正常删除分类
        super.removeById(id);
    }
}
