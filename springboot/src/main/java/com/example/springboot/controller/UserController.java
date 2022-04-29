package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.util.StrUtil;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserMapper userMapper;

    //    @Autowired
//    private UserService userService;
    @Autowired
    private IUserService iuserService;
    @Resource
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        List<User> all = userService.findAll();
        return all;
    }
    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    public boolean add(@RequestBody User user) {
        boolean result = true;
        try
        {
            userService.save(user);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return true;
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        userService.deleteBatchById(ids);
        return true;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return false;
        }
        return iuserService.login(userDTO);
    }
    //    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam(defaultValue = "") String username) {
//        pageNum = (pageNum - 1) * pageSize;
//        username = "%" + username + "%";
//        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
//        Integer total = userMapper.selectTotal(username);
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", data);
//        res.put("total", total);
//        return res;
//    }
    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username,
                               @RequestParam(defaultValue = "") String email,
                               @RequestParam(defaultValue = "") String address) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        queryWrapper.orderByDesc("id");
        return iuserService.page(page, queryWrapper);
    }
}