package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.core.util.StrUtil;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserMapper userMapper;

//    @Autowired
//    private UserService userService;
    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return false;
        }
        return userService.login(userDTO);
    }
    @GetMapping
    public List<User> findAll() {
        List<User> all = userService.list();
        return all;
    }
    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
        return userService.removeByIds(ids);
    }

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
        return userService.page(page, queryWrapper);
    }
}

//package com.example.springboot.controller;
//
//        import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//        import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//        import com.example.springboot.controller.dto.UserDTO;
//        import com.example.springboot.entity.User;
//        import com.example.springboot.service.IUserService;
//        import org.springframework.beans.factory.annotation.Value;
//        import org.springframework.web.bind.annotation.*;
//        import org.springframework.web.multipart.MultipartFile;
//        import cn.hutool.core.util.StrUtil;
//
//        import javax.annotation.Resource;
//        import javax.servlet.ServletOutputStream;
//        import javax.servlet.http.HttpServletResponse;
//        import java.io.InputStream;
//        import java.net.URLEncoder;
//        import java.util.List;
//
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
////    @Autowired
////    private UserMapper userMapper;
//
//    //    @Autowired
////    private UserService userService;
//    @Resource
//    private IUserService userService;
//
//    @PostMapping("/login")
//    public boolean login(@RequestBody UserDTO userDTO) {
//        String username = userDTO.getUsername();
//        String password = userDTO.getPassword();
//        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
//            return false;
//        }
//        return userService.login(userDTO);
//    }
//    @GetMapping
//    public List<User> findAll() {
//        List<User> all = userService.list();
//        return all;
//    }
//    @GetMapping("/{id}")
//    public User findOne(@PathVariable Integer id) {
//        return userService.getById(id);
//    }
//
//    @PostMapping
//    public boolean save(@RequestBody User user) {
//        return userService.saveOrUpdate(user);
//    }
//
//    @DeleteMapping("/{id}")
//    public boolean delete(@PathVariable Integer id) {
//        return userService.removeById(id);
//    }
//
//    @PostMapping("/del/batch")
//    public boolean deleteBatch(@RequestBody List<Integer> ids) { // [1,2,3]
//        return userService.removeByIds(ids);
//    }
//
//    //    @GetMapping("/page")
////    public Map<String, Object> findPage(@RequestParam Integer pageNum,
////                                        @RequestParam Integer pageSize,
////                                        @RequestParam String username) {
////        pageNum = (pageNum - 1) * pageSize;
////        username = "%" + username + "%";
////        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
////        Integer total = userMapper.selectTotal(username);
////        Map<String, Object> res = new HashMap<>();
////        res.put("data", data);
////        res.put("total", total);
////        return res;
////    }
//    @GetMapping("/page")
//    public Page<User> findPage(@RequestParam Integer pageNum,
//                               @RequestParam Integer pageSize,
//                               @RequestParam(defaultValue = "") String username,
//                               @RequestParam(defaultValue = "") String email,
//                               @RequestParam(defaultValue = "") String address) {
//        Page<User> page = new Page<>(pageNum, pageSize);
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        if (!"".equals(username)) {
//            queryWrapper.like("username", username);
//        }
//        if (!"".equals(email)) {
//            queryWrapper.like("email", email);
//        }
//        if (!"".equals(address)) {
//            queryWrapper.like("address", address);
//        }
//        queryWrapper.orderByDesc("id");
//        return userService.page(page, queryWrapper);
//    }
//}



