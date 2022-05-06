package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Role;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iroleService;
    @Resource
    private RoleService roleService;

    @GetMapping
    public Result findAll() {
        List<Role> all = roleService.findAll();
        return Result.success(all);
    }
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(roleService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Role role) {
        boolean result = true;
        try
        {
            roleService.save(role);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return Result.success(result);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        roleService.deleteById(id);
        return Result.success(true);
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        roleService.deleteBatchById(ids);
        return Result.success(true);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String name) {
        Page<Role> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
//        queryWrapper.orderByDesc("id");
        return Result.success(iroleService.page(page, queryWrapper));
    }
}