package com.example.springboot.service;

import com.example.springboot.entity.User;

import java.util.List;

public interface UserService {
    public User findById(int id);
    public List<User> findAll();
    public User findByUsername(String username);
    public User save(User user);
    public List<User> saveAll(List<User> users);
    public User edit(User user);
    public boolean deleteById(int id);
    public boolean deleteBatchById(List<Integer> ids);
}