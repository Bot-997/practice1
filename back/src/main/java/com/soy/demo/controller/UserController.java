package com.soy.demo.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soy.demo.enity.User;
import com.soy.demo.mapper.UserMapper;
import com.soy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    // 导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<User> data = userService.list();
        // hutool 工具类创建writer在内存操作 写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 别名
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("createTime","创建时间");
        writer.addHeaderAlias("avatarUrl","头像");

        // 一次性写出到excel，使用默认样式，强制输出标题
        writer.write(data,true);

        // 设置浏览器响应格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    /**
    * excel导入
    * @param file
    * @throws Exception
    */
    @PostMapping("/import")
    public boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        reader.addHeaderAlias("用户名", "username");
        reader.addHeaderAlias("密码", "password");
        reader.addHeaderAlias("昵称", "nickname");
        reader.addHeaderAlias("邮箱", "email");
        reader.addHeaderAlias("电话", "phone");
        reader.addHeaderAlias("地址", "address");
        reader.addHeaderAlias("创建时间", "createTime");
        reader.addHeaderAlias("头像", "avatarUrl");

        List<User> data = reader.readAll(User.class);
        userService.saveBatch(data);
        return true;
    }
}
