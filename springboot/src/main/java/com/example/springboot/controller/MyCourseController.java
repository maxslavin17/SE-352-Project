package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.MyCourse;
import com.example.springboot.service.MyCourseService;
import com.example.springboot.service.IMyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/mycourse")
public class MyCourseController {

    @Autowired
    private IMyCourseService imycourseService;
    @Resource
    private MyCourseService mycourseService;

    @GetMapping
    public List<MyCourse> findAll() {
        List<MyCourse> all = mycourseService.findAll();
        return all;
    }
    @GetMapping("/{id}")
    public MyCourse findOne(@PathVariable Integer id) {
        return mycourseService.findById(id);
    }

    @PostMapping
    public boolean add(@RequestBody MyCourse mycourse) {
        boolean result = true;
        try
        {
            mycourseService.save(mycourse);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        mycourseService.deleteById(id);
        return true;
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        mycourseService.deleteBatchById(ids);
        return true;
    }

    @GetMapping("/page")
    public Page<MyCourse> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String cname) {
        Page<MyCourse> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MyCourse> queryWrapper = new QueryWrapper<>();
        if (!"".equals(cname)) {
            queryWrapper.like("cname", cname);
        }
        queryWrapper.orderByDesc("id");
        return imycourseService.page(page, queryWrapper);
    }
}