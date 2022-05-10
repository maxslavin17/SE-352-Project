package com.example.springboot.entity;

import javax.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String category;
}
