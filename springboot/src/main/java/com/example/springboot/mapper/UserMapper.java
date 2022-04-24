package com.example.springboot.mapper;

import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * from sys_user")
    List<User> findAll();

    @Insert("INSERT into sys_user(username, password,lastname,email,phone,address) VALUES (#{username}, #{password}," +
            " #{lastname}, #{email}, #{phone}, #{address})")
    int insert(User user);

    int update(User user);

    @Delete("delete from sys_user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);
}
