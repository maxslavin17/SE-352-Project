package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Student;
import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.service.IStudentService;
import org.springframework.stereotype.Service;

// import javax.annotation.Resource;

@Service
public class IStudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
}
