package com.example.springboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "role_menu")
public class RoleMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;
}
