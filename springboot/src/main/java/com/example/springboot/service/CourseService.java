package com.example.springboot.service;

import com.example.springboot.entity.Course;

import java.util.List;

public interface CourseService {
    public Course findById(int id);
    public List<Course> findAll();
    public Course save(Course course);
    public Course edit(Course course);
    public boolean delete(Course course);
    public boolean deleteById(int id);
    public boolean deleteBatchById(List<Integer> ids);

    void setStudentCourse(Integer courseId, Integer studentId);
}
