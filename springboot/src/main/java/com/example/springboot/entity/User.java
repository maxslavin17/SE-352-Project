package com.example.springboot.entity;

import javax.persistence.*;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

//package com.example.springboot.entity;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import java.time.LocalDateTime;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//
//@Data
//@TableName(value = "sys_user")
//public class User {
//    @TableId(type = IdType.AUTO)
//    private Integer id;
//    private String username;
//    @JsonIgnore
//    private String password;
//    private String lastname;
//    private String email;
//    private String phone;
//    private String address;
//    //private LocalDateTime createTime;
//}
