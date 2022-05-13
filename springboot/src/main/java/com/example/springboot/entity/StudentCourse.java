package com.example.springboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "student_course")
public class StudentCourse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stu_id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cou_id;
}
