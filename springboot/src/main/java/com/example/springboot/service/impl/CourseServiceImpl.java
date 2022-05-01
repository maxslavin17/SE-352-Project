package com.example.springboot.service.impl;

import com.example.springboot.entity.Course;
import com.example.springboot.repository.CourseRepository;
import com.example.springboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl  implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course findById(int id) {
        return courseRepository.findById(id).get();
    }
    public List<Course> findAll()
    {
        return courseRepository.findAll();
    }

    public Course save(Course course)
    {
        return courseRepository.save(course);
    }

    public Course edit(Course course)
    {
        return courseRepository.save(course);
    }

    public boolean deleteById(int userId)
    {
        boolean result = true;
        try
        {
            courseRepository.deleteById(userId);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }
    public boolean deleteBatchById(List<Integer> ids) {
        boolean result = true;
        try
        {
            courseRepository.deleteAllByIdInBatch(ids);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }
}