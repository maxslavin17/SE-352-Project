package com.example.springboot.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String lastname;
    private String email;
    private String phone;
    private String address;
}
