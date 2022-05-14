package com.example.springboot.controller;

import com.example.springboot.common.DefConst;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.Course;
import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Autowired
    private IUserService iuserService;

    @GetMapping
    public Result findAll() {
        List<User> all = userService.findAll();
        return Result.success(all);
    }
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(userService.findById(id));
    }
    @GetMapping("/course/{id}")
    public Result findCourse(@PathVariable Integer id) {
        User student = userService.findById(id);
        List<Course> courses = student.getCourses();
//        List<User> all = userService.findAll();
//        List<Course> courses = new ArrayList<>();
//        for (User u : all) {
//            if (id == u.getId()) {
//                courses = u.getCourses();
//            }
//        }
        return Result.success(courses);
    }
    @GetMapping("/username/{username}")
    public Result findByUsername(@PathVariable String username) {
        return Result.success(userService.findByUsername(username));
    }
    @PostMapping
    public Result add(@RequestBody User user) {
        boolean result = true;
        try
        {
            userService.save(user);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return Result.success(result);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success(true);
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        userService.deleteBatchById(ids);
        return Result.success(true);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(DefConst.Code_400, "parameter error");
        }
        UserDTO dto = iuserService.login(userDTO);
        return Result.success(dto);
    }
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        queryWrapper.orderByDesc("id");
        return Result.success(iuserService.page(page, queryWrapper));
    }
}