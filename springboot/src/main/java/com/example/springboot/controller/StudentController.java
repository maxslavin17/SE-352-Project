package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.Student;
import com.example.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;
    // @Autowired
    // private ICourseService icourseService;

    @GetMapping
    public List<Student> findAll() {
        List<Student> all = studentService.findAll();
        return all;
    }
    @GetMapping("/{id}")
    public Student findOne(@PathVariable Integer id) {
        return studentService.findById(id);
    }

    @PostMapping
    public boolean add(@RequestBody Student student) {
        boolean result = true;
        try {
            studentService.save(student);
        } catch(Exception ex) {
            result = false;
        }
        return result;
    }
    // @PostMapping("/studentCourse/{courseId}/{studentId}")
    // public boolean studentCourse(@PathVariable Integer courseId, @PathVariable Integer studentId) {
    //     boolean result = true;
    //     try {
    //         studentService.setStudentCourse(courseId, studentId);
    //     } catch(Exception ex) {
    //         result = false;
    //     }
    //     return result;
    // }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        studentService.deleteById(id);
        return true;
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        studentService.deleteBatchById(ids);
        return true;
    }

    // @GetMapping("/page")
    // public Page<Course> findPage(@RequestParam Integer pageNum,
    //                            @RequestParam Integer pageSize,
    //                            @RequestParam(defaultValue = "") String cname) {
    //     Page<Course> page = new Page<>(pageNum, pageSize);
    //     QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
    //     if (!"".equals(cname)) {
    //         queryWrapper.like("cname", cname);
    //     }
    //     queryWrapper.orderByDesc("id");
    //     return icourseService.page(page, queryWrapper);
    // }
}