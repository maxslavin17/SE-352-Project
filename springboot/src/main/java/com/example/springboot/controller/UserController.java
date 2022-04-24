package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        List<User> all = userMapper.findAll();
        return all;
    }

    @PostMapping
    public Integer save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return userMapper.deleteById(id);
    }

    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username) {
        pageNum = (pageNum - 1) * pageSize;
        username = "%" + username + "%";
        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
        Integer total = userMapper.selectTotal(username);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}


//package com.example.springboot.controller;
//
//        import com.example.springboot.entity.User;
//        import com.example.springboot.mapper.UserMapper;
//        import com.example.springboot.service.UserService;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.web.bind.annotation.*;
//
//        import java.util.List;
//        import java.util.HashMap;
//        import java.util.Map;
//
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<User> findAll() {
//        List<User> all = userMapper.findAll();
//        return all;
//    }
//
//    @PostMapping
//    public Integer save(@RequestBody User user) {
//        return userService.save(user);
//    }
//
//    @DeleteMapping("/{id}")
//    public Integer delete(@PathVariable Integer id) {
//        return userMapper.deleteById(id);
//    }
//
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam String username) {
//        pageNum = (pageNum - 1) * pageSize;
//        username = "%" + username + "%";
//        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
//        Integer total = userMapper.selectTotal(username);
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", data);
//        res.put("total", total);
//        return res;
//    }
//}

