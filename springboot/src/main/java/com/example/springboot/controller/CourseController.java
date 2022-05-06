package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.Course;
import com.example.springboot.service.CourseService;
import com.example.springboot.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;
    @Autowired
    private ICourseService icourseService;

    @GetMapping
    public List<Course> findAll() {
        List<Course> all = courseService.findAll();
        return all;
    }
    @GetMapping("/{id}")
    public Course findOne(@PathVariable Integer id) {
        return courseService.findById(id);
    }

    @PostMapping
    public boolean add(@RequestBody Course course) {
        boolean result = true;
        try
        {
            courseService.save(course);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        courseService.deleteById(id);
        return true;
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        courseService.deleteBatchById(ids);
        return true;
    }

    @GetMapping("/page")
    public Page<Course> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String cname) {
        Page<Course> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (!"".equals(cname)) {
            queryWrapper.like("cname", cname);
        }
        queryWrapper.orderByDesc("id");
        return icourseService.page(page, queryWrapper);
    }
}