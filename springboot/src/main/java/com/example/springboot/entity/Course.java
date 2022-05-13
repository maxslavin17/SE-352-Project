package com.example.springboot.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cid;
    private String cname;
    private String description;

    @ManyToMany(mappedBy = "courses")
    @TableField(exist = false)
    private List<User> users = new ArrayList<>();
}
