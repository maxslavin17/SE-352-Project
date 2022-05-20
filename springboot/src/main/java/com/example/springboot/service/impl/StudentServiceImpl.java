package com.example.springboot.service.impl;

import com.example.springboot.entity.Student;
// import com.example.springboot.entity.User;
import com.example.springboot.repository.StudentRepository;
// import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl  implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    // @Autowired
    // private UserRepository userRepository;

    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }
    public List<Student> findAll()
    {
        return studentRepository.findAll();
    }

    public Student save(Student student)
    {
        return studentRepository.save(student);
    }

    public Student edit(Student student)
    {
        return studentRepository.save(student);
    }

    public boolean deleteById(int userId)
    {
        boolean result = true;
        try
        {
            studentRepository.deleteById(userId);
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
            studentRepository.deleteAllByIdInBatch(ids);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }
    // @Override
    // public void setStudentCourse(Integer courseId, Integer studentId) {
    //     User student = userRepository.getById(studentId);
    //     Course course = courseRepository.getById(courseId);
    //     student.getCourses().add(course);
    //     courseRepository.save(course);
    // }
}
