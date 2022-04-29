package com.example.springboot.service;

import com.example.springboot.entity.User;

import java.util.List;

public interface UserService {
    public User findById(int id);
    public List<User> findAll();
    public User save(User user);
    public User edit(User user);
    public boolean deleteById(int id);
    public boolean deleteBatchById(List<Integer> ids);
}
//package com.example.springboot.service;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.example.springboot.entity.User;
//import com.example.springboot.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
////@Service
//public class UserService extends ServiceImpl<UserMapper, User> {
//    public boolean saveUser(User user) {
//        if (user.getId() == null) {
//            return save(user);
//        } else {
//            return updateById(user);
//        }
//        //return saveOrUpdate(user);
//    }
//
////    @Autowired
////    private UserMapper userMapper;
////
////    public int save(User user) {
////        if (user.getId() == null) {
////            return userMapper.insert(user);
////        } else {
////            return userMapper.update(user);
////        }
////    }
//}

