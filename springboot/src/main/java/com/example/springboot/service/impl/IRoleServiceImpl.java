package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Role;
import com.example.springboot.mapper.RoleMapper;
import com.example.springboot.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}
