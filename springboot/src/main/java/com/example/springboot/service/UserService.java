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