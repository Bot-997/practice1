package com.soy.demo.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
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