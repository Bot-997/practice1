package com.soy.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soy.demo.enity.User;
import com.soy.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 服务层 存放业务逻辑处理，也是一些关于数据库处理的操作，但不是直接和数据库打交道
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public boolean saveUser(User user) {
        // mybatis-plus提供的方法
        return saveOrUpdate(user);
    }

//    @Autowired
//    private UserMapper userMapper;
//
//    public int save(User user){
//        if(user.getId() == null){
//            return userMapper.insert(user);
//        }else {
//            return userMapper.update(user);
//        }
//    }
}
