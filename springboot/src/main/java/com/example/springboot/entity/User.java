package com.example.springboot.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
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
}