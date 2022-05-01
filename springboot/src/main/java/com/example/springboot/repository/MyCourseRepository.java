package com.example.springboot.repository;

import com.example.springboot.entity.MyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCourseRepository extends JpaRepository<MyCourse, Integer> {
}
