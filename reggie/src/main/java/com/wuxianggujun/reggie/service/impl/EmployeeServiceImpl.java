package com.wuxianggujun.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxianggujun.reggie.entity.Employee;
import com.wuxianggujun.reggie.mapper.EmployeeMapper;
import com.wuxianggujun.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
