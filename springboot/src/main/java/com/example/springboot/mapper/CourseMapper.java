package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//@Mapper
public interface CourseMapper extends BaseMapper<Course> {
//    void deleteStudentCourse(@Param("courseId")Integer courseId, @Param("studentId") Integer studentId);
//
//    void setStudentCourse(@Param("courseId")Integer courseId, @Param("studentId") Integer studentId);
}

