package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.Course;
import com.example.springboot.entity.MyCourse;
import com.example.springboot.entity.User;
import com.example.springboot.service.CourseService;
import com.example.springboot.service.MyCourseService;
import com.example.springboot.service.IMyCourseService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/mycourse")
public class MyCourseController {
    @Resource
    private MyCourseService mycourseService;
    @Resource
    private CourseService courseService;
    @Resource
    private UserService userService;
    @Autowired
    private IMyCourseService imycourseService;

    @GetMapping
    public List<MyCourse> findAll() {
        List<MyCourse> all = mycourseService.findAll();
        return all;
    }
    @GetMapping("/user/{uid}")
    public List<MyCourse> findMyCourses(@PathVariable Integer uid) {
        User student = userService.findById(uid);
        List<Course> courses = student.getCourses();
        List<MyCourse> myCourses = courses.stream()
                .map(obj -> new MyCourse(obj.getId(), obj.getCid(), obj.getCname(), obj.getDescription()))
                .collect(Collectors.toList());
        return myCourses;
    }
    @GetMapping("/{id}")
    public MyCourse findOne(@PathVariable Integer id) {
        return mycourseService.findById(id);
    }
    @PostMapping("/saveAll")
    public boolean saveAll(@RequestBody List<MyCourse> myCourses) {
        boolean result = true;
        try
        {
            mycourseService.saveAll(myCourses);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
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
    @DeleteMapping("/user/{uid}/{cid}")
    public boolean deleteMyCourse(@PathVariable Integer uid, @PathVariable Integer cid) {
//        User student = userService.findById(uid);
//        List<Course> courses = student.getCourses();
//        courses.removeIf(c -> (c.getId() == cid));
//        return true;
        User student = userService.findById(uid);
        Course course = courseService.findById(cid);
        student.getCourses().remove(course);
        courseService.save(course);
        return true;
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
                               @RequestParam(defaultValue = "") String mycname) {
        Page<MyCourse> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MyCourse> queryWrapper = new QueryWrapper<>();
        if (!"".equals(mycname)) {
            queryWrapper.like("mycname", mycname);
        }
//        queryWrapper.orderByDesc("id");
        return imycourseService.page(page, queryWrapper);
    }
}