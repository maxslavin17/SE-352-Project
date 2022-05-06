package com.example.springboot.service.impl;

import com.example.springboot.entity.Role;
import com.example.springboot.repository.RoleRepository;
import com.example.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findById(int id) {
        return roleRepository.findById(id).get();
    }
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role edit(Role role) {
        return roleRepository.save(role);
    }

    public boolean deleteById(int userId) {
        boolean result = true;
        try
        {
            roleRepository.deleteById(userId);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }
    public boolean deleteBatchById(List<Integer> ids) {
        boolean result = true;
        try
        {
            roleRepository.deleteAllByIdInBatch(ids);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }
}
