package com.example.springboot.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    @JsonIgnore
    private String password;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    //private LocalDateTime createTime;
    private String role;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="student_course",
                uniqueConstraints = {@UniqueConstraint(columnNames = {"stu_id","cou_id"})},
                joinColumns = {@JoinColumn(name="stu_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name="cou_id", referencedColumnName = "id")}
    )
    @TableField(exist = false)
    @ToString.Exclude
    private List<Course> courses = new ArrayList<Course>();
}