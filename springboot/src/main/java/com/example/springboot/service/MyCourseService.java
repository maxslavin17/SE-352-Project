package com.example.springboot.service;

import com.example.springboot.entity.MyCourse;

import java.util.List;

public interface MyCourseService {
    public MyCourse findById(int id);
    public List<MyCourse> findAll();
    public MyCourse save(MyCourse mycourse);
    public MyCourse edit(MyCourse mycourse);
    public boolean deleteById(int id);
    public boolean deleteBatchById(List<Integer> ids);
}
