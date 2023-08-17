package com.soy.demo.enity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName(value = "sys_user") // 告诉mybatis-plus该实体类对应的表
public class User {
    @TableId
    private Integer id;
    private String username;
    // 隐藏密码
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
}
