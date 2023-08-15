package com.soy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soy.demo.enity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

// Mapper层=dao层 对数据库进行数据持久化操作
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from sys_user")
    List<User> findAll();

    @Insert("INSERT into sys_user(username, password, nickname, email, phone, address) VALUES (#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{address})")
    int insert(User user);

    int update(User user);

    @Delete("delete from sys_user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    @Select("select * from sys_user where username like concat('%', #{username}, '%') limit #{pageNum}, #{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String username);

    @Select("select count(id) from sys_user where username like concat('%', #{username}, '%')")
    Integer selectTotal();
}
