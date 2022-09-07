package com.wuxianggujun.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Ա��ʵ��
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 2516055735569592064L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;//���֤����

    private Integer status;

    @TableField(fill = FieldFill.INSERT)//����ʱ����ֶ�
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)//����͸���ʱ����ֶ�
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)//����ʱ����ֶ�
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)//����͸���ʱ����ֶ�
    private Long updateUser;

}
