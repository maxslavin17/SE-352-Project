package com.example.springboot.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String stu_id;
    private String stu_lastname;
    private String email;
//    @ManyToMany(fetch=FetchType.EAGER)
//    @JoinTable(name="student_course", uniqueConstraints = {@UniqueConstraint(columnNames = {"s_id","c_id"})},
//                joinColumns = {@JoinColumn(name="s_id", referencedColumnName = "id")},
//                inverseJoinColumns = {@JoinColumn(name="c_id", referencedColumnName = "id")}
//    )
//    private List<Course> courses = new ArrayList<>();

}
