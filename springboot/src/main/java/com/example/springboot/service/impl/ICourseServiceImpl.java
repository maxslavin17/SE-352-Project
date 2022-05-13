package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Course;
import com.example.springboot.mapper.CourseMapper;
import com.example.springboot.service.ICourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ICourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
//    @Resource
//    private CourseMapper courseMapper;
//
//    @Override
//    public void setStudentCourse(Integer courseId, Integer studentId) {
////        courseMapper.deleteStudentCourse(courseId, studentId);
////        courseMapper.setStudentCourse(courseId, studentId);
//    }
}
