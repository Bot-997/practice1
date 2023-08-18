package com.soy.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soy.demo.enity.User;
import com.soy.demo.mapper.UserMapper;
import com.soy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 控制器，导入service层，因为service中的方法是我们使用到的，controller通过接收前端传过来的参数进行业务操作，在返回一个指定的路径或者数据表。
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 新增与修改
    @PostMapping()
    // @RequestBody 把请求中的json转换为目标类型 字段名称需要一一对应
    public boolean save(@RequestBody User user){
        return userService.saveUser(user);
    }

    // 查询所有
    @GetMapping()
    public List<User> findAll(){
        return userService.list();
    }

    // 删除
    @DeleteMapping("del/{id}")
    // @PathVariable("xx") 接收请求路径中占位符的值 如@PathVariable("id")接收/{id}
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeBatchByIds(ids);
    }
//    // 查询分页 手写
//    // @RequestParam接受 ?pageNum=1&pageSize=10 中两个对应值
//    @GetMapping("/page")
//    public Map<String,Object>  findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam String username){
//        pageNum = (pageNum-1) * pageSize;
//        Map<String,Object> res = new HashMap<>();
//        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
//        Integer total = userMapper.selectTotal();
//        res.put("data", data);
//        res.put("total", total);
//        return res;
//    }

    // 分页查询 mybatis-plus框架
    @GetMapping("/page/desc")
    public IPage<User> findPageDesc(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!"".equals(username)){
            queryWrapper.like("username", username);
        }
        if(!"".equals(email)){
            queryWrapper.like("email", email);
        }
        if(!"".equals(address)){
            queryWrapper.like("address", address);
        }
        queryWrapper.orderByDesc("id");
        return userService.page(page,queryWrapper);
    }

    @GetMapping("/page/asc")
    public IPage<User> findPageAsc(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!"".equals(username)){
            queryWrapper.like("username", username);
        }
        if(!"".equals(email)){
            queryWrapper.like("email", email);
        }
        if(!"".equals(address)){
            queryWrapper.like("address", address);
        }
        queryWrapper.orderByAsc("id");
        return userService.page(page,queryWrapper);
    }
}
