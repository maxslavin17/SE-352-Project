package com.example.springboot.service;

import com.example.springboot.entity.Role;

import java.util.List;

public interface RoleService {
    public Role findById(int id);
    public List<Role> findAll();
    public Role save(Role role);
    public Role edit(Role role);
    public boolean deleteById(int id);
    public boolean deleteBatchById(List<Integer> ids);
}
