package com.example.springboot.service.impl;

import com.example.springboot.entity.MyCourse;
import com.example.springboot.repository.MyCourseRepository;
import com.example.springboot.service.MyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCourseServiceImpl implements MyCourseService {
    @Autowired
    private MyCourseRepository mycourseRepository;

    public MyCourse findById(int id) {
        return mycourseRepository.findById(id).get();
    }
    public List<MyCourse> findAll()
    {
        return mycourseRepository.findAll();
    }

    public MyCourse save(MyCourse mycourse) {
        return mycourseRepository.save(mycourse);
    }
    public List<MyCourse> saveAll(List<MyCourse> myCourses) {
        return mycourseRepository.saveAll(myCourses);
    }
    public MyCourse edit(MyCourse mycourse)
    {
        return mycourseRepository.save(mycourse);
    }

    public boolean deleteById(int userId)
    {
        boolean result = true;
        try
        {
            mycourseRepository.deleteById(userId);
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
            mycourseRepository.deleteAllByIdInBatch(ids);
        }
        catch(Exception ex)
        {
            result = false;
        }
        return result;
    }
}
