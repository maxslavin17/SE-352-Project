package com.example.springboot.service;

import com.example.springboot.entity.Student;

import java.util.List;

public interface StudentService {
    public Student findById(int id);
    public List<Student> findAll();
    public Student save(Student student);
    public Student edit(Student student);
    public boolean deleteById(int id);
    public boolean deleteBatchById(List<Integer> ids);

    // void setStudentCourse(Integer courseId, Integer studentId);
}
