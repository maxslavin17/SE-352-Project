package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.Student;
import com.example.springboot.service.StudentService;
import com.example.springboot.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;
    @Autowired
    private IStudentService istudentService;

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

    @GetMapping("/page")
    public Page<Student> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String sname) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (!"".equals(sname)) {
            queryWrapper.like("sname", sname);
        }
        queryWrapper.orderByDesc("id");
        return istudentService.page(page, queryWrapper);
    }
}