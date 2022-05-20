package com.example.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "my_course")
public class MyCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mycid;
    private String mycname;
    private String mydescription;

    public MyCourse(Integer id, String cid, String cname, String description) {
        this.id = id;
        mycid = cid;
        mycname = cname;
        mydescription = description;
    }
//    @ManyToMany(mappedBy = "courses")
//    private List<Student> students = new ArrayList<>();
}
