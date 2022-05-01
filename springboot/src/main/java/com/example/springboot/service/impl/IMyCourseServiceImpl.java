package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.MyCourse;
import com.example.springboot.mapper.MyCourseMapper;
import com.example.springboot.service.IMyCourseService;
import org.springframework.stereotype.Service;

@Service
public class IMyCourseServiceImpl extends ServiceImpl<MyCourseMapper, MyCourse> implements IMyCourseService {
}
